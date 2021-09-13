package com.java.controller.admin;

import com.yuqing.common.FastDFSClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * description：
 * author：丁鹏
 * date：16:17
 */
@Controller
@RequestMapping("/upload")
public class UploadFileController {

    /**
     * 文件上传-fastDFS
     * @param wenJian
     * @return
     */
    @RequestMapping("/uploadFile")
    public @ResponseBody Map<String,Object> uploadFile(MultipartFile wenJian){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            FastDFSClient client = new FastDFSClient("classpath:resources/fdfs_client.conf");
            //取出上传文件的原始名
            String oriName = wenJian.getOriginalFilename();//1.2.3.jpg
            String ext = oriName.substring(oriName.lastIndexOf(".")+1);
            //url=图片在服务器中保存的地址
            String url =client.uploadFile(wenJian.getBytes(),ext);
            //status=0/1
            resultMap.put("status","0");
            resultMap.put("url","http://192.168.25.133/"+url);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","1");//上传失败
            return resultMap;
        }
    }

}
