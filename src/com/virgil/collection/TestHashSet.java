package com.virgil.collection;

import java.util.HashSet;
import java.util.Iterator;

import com.virgil.util.LogUtil;

/**
 * Created by liuwujing on 15/2/5.
 */
public class TestHashSet {
    public static void main(String[] args){
        testDuplicateHashSet();
        String a=new String("this is a test String");
        String b=new String("this is a test String");
        LogUtil.printlnInConsle("");

    }

    private static void testDuplicateHashSet(){
        HashSet<String> hs=new HashSet<>();
        String a=new String("this is a test String");
        String b=new String("this is a test String");
        hs.add(a);
        hs.add(b);
        Iterator<String> ite=hs.iterator();
        while (ite.hasNext()){
            LogUtil.printlnInConsle(ite.next());
        }
    }
}
