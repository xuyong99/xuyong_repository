package com.java.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * description：
 * author：丁鹏
 * date：15:53
 */
public class MongoUtil {
    private static MongoCollection<Document> userCollection=null;
    private static MongoClient client = null;


    static{
        try {
            //1、创建Properties对象来表示mongo.properties文件
            Properties prop = new Properties();
            //2、关联
            InputStream ins = MongoUtil.class.getClassLoader().getResourceAsStream("mongo.properties");
            prop.load(ins);
            //3、获取文件中的数据
            String host = prop.getProperty("host");
            String port = prop.getProperty("port");
            String databaseName = prop.getProperty("databaseName");
            String collectionName = prop.getProperty("collectionName");
            //4、关流
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取集合
     * @return
     */
    public static MongoCollection<Document> getCollection(){
        //1、连接上MongoDB
        client = new MongoClient("127.0.0.1",27017);
        //2、连接上指定的库
        MongoDatabase db = client.getDatabase("k8512");
        //3、连上指定的集合
        userCollection = db.getCollection("discuss");
        return userCollection;
    }

    /**
     * 关闭MongoDB的客户端
     */
    public static void close(){
        if(client!=null)
            client.close();
    }






}
