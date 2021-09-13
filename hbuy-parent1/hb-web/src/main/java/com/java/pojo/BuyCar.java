package com.java.pojo;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * description：购物车
 * author：丁鹏
 * date：10:45
 */

public class BuyCar implements Serializable{

    private static final long serialVersionUID = 4282685045524045824L;

    private List<Good> goodList;

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.goodList = goodList;
    }

    /**
     *判断指定的商品在购物车中是否存在
     * @param productNum 商品编号
     * @return -1不存在，>0则代表商品对象再list集合中的下标位置
     */
    public int goodIsExistInBuyCar(String productNum){
        if(StrUtil.isBlank(productNum) || StrUtil.isNullOrUndefined(productNum)){
            return -1;
        }
        if(this.goodList==null || this.goodList.size()==0){
            return -1;
        }
        boolean flag = false;//指定编号的商品在购物车中不存在
        int i=0;
        for(;i<this.goodList.size();i++){
            Good temp = goodList.get(i);
            if(productNum.equals(temp.getProductNum())){
                flag=true;
                break;
            }
        }
        if(flag){
            return i;
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "BuyCar{" +
                "goodList=" + goodList +
                '}';
    }

    /**
     * 将cookie中的购物车商品与redis中的购物车商品合并
     * @param redisBuyCar
     * @return
     */
    public BuyCar hebing(BuyCar redisBuyCar) {
        //取出cookie中的购物车中的list集合
        List<Good> cookieGoodList =  this.goodList;//10个
        //取出redis中的购物车list集合
        List<Good> redisGoodList = redisBuyCar.goodList;//20个
        //开始合并redis购物车与cookie购物车中相同的商品
        List<Good> tempGoodList = new ArrayList<>(cookieGoodList.size());//最终redis购物车与cookie购物车合并后的大购物车
        //Collections.copy(tempGoodList,cookieGoodList);
        tempGoodList.addAll(cookieGoodList);
        //存放redis购物车和cookie购物车中不同的商品
        List<Good> aGoodList = new ArrayList<>(redisGoodList.size());
        //Collections.copy(aGoodList,redisGoodList);
        aGoodList.addAll(redisGoodList);
        for(int i = 0;i<cookieGoodList.size();i++){//cookieGoodList
            for(int j=0;j<redisGoodList.size();j++){//redisGoodList
                if(cookieGoodList.get(i).getProductNum().equals(redisGoodList.get(j).getProductNum())){//商品一样
                    //合并
                    int num = cookieGoodList.get(i).getNum()+redisGoodList.get(j).getNum();
                    Good good = tempGoodList.get(i);
                    good.setNum(num);
                    tempGoodList.set(i,good);
                    //从aGoodList中移除相同的商品
                    aGoodList.set(j,null);
                    break;
                }
            }
        }
        //除去aGoodList中标记为null的数据
        ListIterator<Good> it = aGoodList.listIterator();
        while(it.hasNext()){
            Good good = it.next();
            if(good==null){
                it.remove();
            }
        }
        //往tempGoodList中添加不同的商品
        if(aGoodList!=null && aGoodList.size()>=1){
            tempGoodList.addAll(aGoodList);
        }
        BuyCar buyCar = new BuyCar();
        buyCar.setGoodList(tempGoodList);
        return buyCar;
    }

    /**
     * 统计购物车中商品的数量
     * @return
     */
    public int countBuyCarGoodNum(){
        List<Good> goodList = this.goodList;
        int num = 0;
        for (Good good : goodList){
            num+=good.getNum();
        }
        return num;
    }
}
