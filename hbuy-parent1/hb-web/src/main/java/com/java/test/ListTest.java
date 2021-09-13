package com.java.test;

import com.java.pojo.BuyCar;
import com.java.pojo.Good;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * description：
 * author：丁鹏
 * date：10:46
 */
public class ListTest {
    public static void main(String[] args) {
        List<Good> goodList = new ArrayList<>();
        Good good1 = new Good("88081",10);
        Good good2 = new Good("88082",10);
        Good good3 = new Good("88083",10);
        goodList.add(good1);
        goodList.add(null);
        goodList.add(good2);
        goodList.add(null);
        goodList.add(good3);
        System.out.println(goodList.size());
        ListIterator<Good> it = goodList.listIterator();
        while(it.hasNext()){
            Good good = it.next();
            if(good==null){
                it.remove();
            }
        }
        System.out.println(goodList.size()+"----"+goodList);
    }
}
