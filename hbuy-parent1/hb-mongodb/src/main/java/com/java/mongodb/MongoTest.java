package com.java.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * description：
 * author：丁鹏
 * date：16:37
 */
public class MongoTest {
    private MongoCollection<Document> userCollection=null;
    private MongoClient client = null;

    @Before
    public void init(){
        //1、连接上MongoDB
        client = new MongoClient("127.0.0.1",27017);
        //2、连接上指定的库
        MongoDatabase db = client.getDatabase("k8512");
        //3、连上指定的集合
        //userCollection = db.getCollection("users");
        userCollection = db.getCollection("discuss");
    }

    /**
     * 插入一条数据
     */
    @Test
    public void insertOne(){
        //4、对集合进行操作
        //方式1:将数据封装到document中去,Document类型本质上是Map集合
        Document doc1 = new Document();
        doc1.append("uName","大详");
        doc1.append("gender","男");
        doc1.append("age",16);
        //方式2：
        Document doc2 = Document.parse("{\"uName\":\"大详\",\"gender\":\"男\",\"age\":15}");
        userCollection.insertOne(doc2);

    }

    /**
     * 测试性能
     */
    @Test
    public void insert2(){
        long startTime = System.currentTimeMillis();
        //4、对集合进行操作
        for(int i=1;i<=20000;i++){
            //方式1:将数据封装到document中去,Document类型本质上是Map集合
            Document doc1 = new Document();
            doc1.append("uName","王紧-"+i);
            doc1.append("gender","男");
            doc1.append("age",i);
            //开始插入
            userCollection.insertOne(doc1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("共消耗"+(endTime-startTime)/1000+"s");
    }

    /**
     * 同时插入多条数据
     */
    @Test
    public void insertMany(){
        Document doc1 = new Document();
        doc1.append("uName","大详");
        doc1.append("gender","男");
        doc1.append("age",20);

        Document doc2 = new Document();
        doc2.append("uName","王紧");
        doc2.append("gender","男");
        doc2.append("age",18);

        Document doc3 = new Document();
        doc3.append("uName","叶含");
        doc3.append("gender","男");
        doc3.append("age",16);

        Document doc4 = new Document();
        doc4.append("uName","大伟");
        doc4.append("gender","男");
        doc4.append("age",16);

        userCollection.insertMany(Arrays.asList(doc1,doc2,doc3,doc4));
    }

    //带分页的查询数据
    @Test
    public void select(){
        FindIterable<Document> documents = userCollection.find();
        documents.skip(0).limit(10);
        documents.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //带条件的查询-将条件封装成Document对象
    @Test
    public void select2(){
        //查询users集合中uName='小翠儿'的用户
        //方式1：因为BSON是Document类的父类型，传递Document对象
        //构建Document对象，有2种方式
        //Document doc1 = new Document("uName","小翠儿");
        //构建Document第2种方式
        Document doc1 = Document.parse("{\"uName\":\"小翠儿\"}");
        //doc1.append("uName","小翠儿");
        FindIterable<Document> docs = userCollection.find(doc1);
        //遍历结果
        docs.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //带条件的查询-将条件封装成BSON对象
    @Test
    public void select3(){
        //使用工具类将条件构建成BSON
        //查询users集合中uName='小翠儿'的数据
        Bson bson1 = Filters.eq("uName", "小翠儿");
        FindIterable<Document> docs = userCollection.find(bson1);
        //遍历结果
        docs.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //查询users集合中uName="凤姐"并且gender="男"
    @Test
    public void select4(){
        Document doc1 = Document.parse("{$and:[{\"uName\":\"凤姐\"},{\"gender\":\"男\"}]}");
        FindIterable<Document> docs = userCollection.find(doc1);
        docs.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //查询users集合中uName="凤姐"或者gender="男"
    @Test
    public void test5(){
        Bson bson1 = Filters.eq("uName", "凤姐");
        Bson bson2 = Filters.eq("gender", "男");
        Bson bson3 = Filters.or(bson1, bson2);
        FindIterable<Document> docs = userCollection.find(bson3);
        docs.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //找出users集合中12<age<50的所有用户
    @Test
    public void select6(){
        Bson bson1 = Filters.gt("age", 12);
        Bson bson2 = Filters.lt("age", 50);
        Bson bson3 = Filters.and(bson1, bson2);
        FindIterable<Document> docs = userCollection.find(bson3);
        docs.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //找出users集合中12<age<50的所有用户
    @Test
    public void select7(){
        Document doc1 = Document.parse("{$and:[{\"age\":{$gt:12}},{\"age\":{$lt:50}}]}");
        FindIterable<Document> docs = userCollection.find(doc1);
        docs.iterator().forEachRemaining(temp-> System.out.println(temp));
    }

    //------------------------------MongodB的修改与删除-----------------------------------
    //删除users集合中age>80的所有数
    @Test
    public void remove(){
        Document doc1 = Document.parse("{\"age\":{$gt:80}}");
        DeleteResult deleteResult = userCollection.deleteMany(doc1);
        System.out.println("deleteResult="+deleteResult);
    }

    //将users集合中age=30岁的数据中的uName修改"bigbird"
    @Test
    public void update(){
        Document doc1 = Document.parse("{\"age\":30}");
        Document doc2 = Document.parse("{$set:{\"uName\":\"王紧-30\"}}");
        UpdateResult updateResult = userCollection.updateOne(doc1, doc2);
        System.out.println("updateResult="+updateResult);
    }


    @After
    public void close(){
        //5、关闭连接
        client.close();
    }

}
