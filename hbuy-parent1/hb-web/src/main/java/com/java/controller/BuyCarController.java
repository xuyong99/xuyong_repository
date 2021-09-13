package com.java.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.java.pojo.BuyCar;
import com.java.pojo.Good;
import com.java.utils.Base64Utils;
import com.netflix.client.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：10:49
 */
@Controller
@RequestMapping("/buycar")
public class BuyCarController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public @ResponseBody String login(String uName,String password,HttpSession session){
        session.setAttribute("uName",uName);
        return "login success";
    }

    /**
     * 将商品加入购物车
     * @return
     */
    @RequestMapping("/add2BuyCar")
    public @ResponseBody Map<String,Object> add2BuyCar(String productNum, Integer num, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");//成功
        try {
            //1、校验productNum、num的格式合法性
            //2、首先判断是否登录
            Object uName = session.getAttribute("uName");
            //保存在cookie中的购物车的key="buycar"
            //获取cookie中的购物车
            Cookie[] cookies = request.getCookies();
            //定义标记
            boolean flag = false;//购物车是空的
            String cookieValue=null;
            //公共部分
            BuyCar buyCar = null;
            //判断cookies是否为null
            if(cookies!=null && cookies.length>=1){
                //判断cookie中是否存在购物车，并且购物车中有商品
                for (int i=0;i<cookies.length;i++){
                    String cookieName = cookies[i].getName();
                    if("buycar".equals(cookieName)){
                        cookieValue = cookies[i].getValue();
                        //购物车中有商品
                        if(!StrUtil.isBlank(cookieValue) || !StrUtil.isNullOrUndefined(cookieValue)){
                            flag=true;//购物车中有商品
                            break;
                        }
                    }
                }
            }
            if(flag){//存在商品
                //从cookie中获取商品加密的字符串
                //解密
                String jsonBuyCar = Base64Utils.getFromBASE64(cookieValue);
                //将json字符串转成BuyCar对象   88081   3
                buyCar = JSON.parseObject(jsonBuyCar, BuyCar.class);
                //判断购物车中是否存在已知的相似商品
                int i = buyCar.goodIsExistInBuyCar(productNum);
                if(i==-1){//如果不存在，则创建一个good对象，并且将good对象封装到BuyCar中去
                    Good good = new Good(productNum,num);
                    buyCar.getGoodList().add(good);
                }else{ //如果存在，则修改购物车中某个商品的数量
                    Good good = buyCar.getGoodList().get(i);
                    good.setNum(good.getNum()+num);
                    buyCar.getGoodList().set(i,good);
                }
            }else{//不存在商品
                //首先将商品编号、商品购买数量封装成Good对象
                Good good = new Good(productNum,num);
                //将商品对象good加入到购物车
                buyCar = new BuyCar();
                List<Good> goodList = Arrays.asList(good);
                buyCar.setGoodList(goodList);
            }
            if(uName==null || StrUtil.isNullOrUndefined((String)uName)){//未登录
                //将buycar对象变成json字符串对象(加密的前提是数据要是字符串)
                String buyCarJson = JSON.toJSONString(buyCar);
                //首先对购物车加密
                String miWen = Base64Utils.getBASE64(buyCarJson);
                //去掉密文中的空格和回车，cookie值中不能有空格
                miWen = miWen.replaceAll("\r\n","");
                //将购物车加入到Cookie中去
                Cookie cookie = new Cookie("buycar",miWen);
                //设置产生cookie的路径
                cookie.setPath("/buycar");
                //设置Cookie失效时间(24小时)
                cookie.setMaxAge(3600*32);
                //将cookie回写给浏览器端
                response.addCookie(cookie);
            }else{//登录
                //判断redis中是否存在购物车，并且购物车中有商品
                ValueOperations vop = redisTemplate.opsForValue();
                Object bc = vop.get("buycar"+(String)uName);
                if(bc==null){//redis中没有购物车
                    //将处理好的buycar对象存放到redis中去
                    vop.set("buycar"+(String)uName,buyCar);
                }else{//redis中有购物车
                    //将redis的购物车与cookie中的购物车(buycar)合并
                    BuyCar redisBuyCar = (BuyCar) bc;//redis中的购物车
                    BuyCar bigBuyCar = buyCar.hebing(redisBuyCar);
                    //覆盖redis中的购物车
                    vop.set("buycar"+(String)uName,bigBuyCar);
                    //给全局变量buycar赋值合并后的最新值
                    buyCar = bigBuyCar;
                }
                //清空cookie
                Cookie cookie = new Cookie("buycar","");
                cookie.setPath("/buycar");//此处与109行代码保持一致，否则cookie清空会失败
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            //购物车中商品的总数量
            resultMap.put("goodNum",buyCar.countBuyCarGoodNum());
            //购物车中商品的种类数量
            resultMap.put("goodTypeNum",buyCar.getGoodList().size());
            //购物车中所有商品的总价格--->根据商品编号查询出某个商品的单价，然后进行计算
            resultMap.put("cost",1000F);
            return resultMap;
        } catch (Exception e) {
            //添加购物车失败
            resultMap.put("status","1");
            return resultMap;
        }

    }

    /**
     * 查看购物车
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/findGoodInBuyCar")
    public @ResponseBody List<Map<String,Object>> findGoodInBuyCar(HttpSession session,HttpServletRequest request){
        try {
            //定义零时变量记录购物车中的商品列表
            List<Good> tempGoodList  = null;
            //1、判断用户是登录
            Object uName = session.getAttribute("uName");
            //(1)登陆成功
            if(uName!=null && StrUtil.isBlank((String)uName)){
                //登录成功后，数据保存在redis中
                //从redis中获取购物车
                ValueOperations vop = redisTemplate.opsForValue();
                Object o = vop.get("buycar" + (String) uName);
                if(o!=null){
                    tempGoodList = ((BuyCar)o).getGoodList();
                }
            }else{
                //(2)登录失败
                Cookie[] cookies = request.getCookies();
                //遍历cookies取出名字为buycar的购物车数据
                String cookieValue = null;
                for (int i = 0; cookies!=null && i <cookies.length ; i++) {
                    Cookie cookie = cookies[i];
                    //找到了指定的cookie
                    if("buycar".equals(cookie.getName())){
                        cookieValue = cookie.getValue();
                        break;
                    }
                }
                //判断是否找到cookie
                if(cookieValue!=null && !StrUtil.isBlank(cookieValue)){
                    //通过base64解密
                    String jsonBuyCar = Base64Utils.getFromBASE64(cookieValue);
                    //将json字符串对象转成BuyCar对象
                    BuyCar cookieBuyCar = JSON.parseObject(jsonBuyCar, BuyCar.class);
                    tempGoodList = cookieBuyCar.getGoodList();
                }
            }
            //遍历goodList集合，取出每个商品的商品编号(productNum)
            //根据商品编号，查询数据库，取出每个商品的详情
            System.out.println("购物车："+tempGoodList);
            return null;
        } catch (Exception e) {
            System.out.println("购物车：购物车发生异常了");
            return null;
        }
    }

}
