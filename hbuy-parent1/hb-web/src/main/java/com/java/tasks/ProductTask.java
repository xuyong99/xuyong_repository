package com.java.tasks;

import com.java.service.ProductService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:11
 */
@Component
public class ProductTask {

    @Autowired
    private ProductService productService;

    @Autowired
    private Configuration config;

    /**
     * 根据Product.ftl模板生成对应的静态网页
     * @return
     */
    @Scheduled(cron = "0/20 * * * * *")
    public void generateProductHTMLByFM() throws Exception {
        System.out.println("--------------------------------");
        //1、从数据库中查询产品的具体信息
        List<Map<String, Object>> productList = productService.findProducts();
        for(int i = 0;productList!=null && i<productList.size();i++){
            //取出某个商品的编号
            Map<String, Object> dataMap = productList.get(i);
            String productNum = (String) dataMap.get("productNum");
            //取出商品主键
            Long productId = (Long) dataMap.get("id");
            List<String> imgUrlList  = productService.findProductImgByPID(productId);
            dataMap.put("imgUrlList",imgUrlList);
            //2、获取代表flt文件的模板对象
            Template template = config.getTemplate("Product.ftl");
            //3、静态文件保存的位置
            File file = new File("D:\\hbuy\\productDetail\\"+productNum+".html");
            FileWriter fw = new FileWriter(file);
            //4、通过freemaker生成数据
            template.process(dataMap,fw);
            //5、关流
            fw.close();
        }
    }

}
