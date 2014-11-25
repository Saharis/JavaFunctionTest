package com.virgil.test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.xmlbeans.impl.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.virgil.util.FileUtil;
import com.virgil.util.LogUtil;
import ctrip.business.enumclass.BasicBusinessTypeEnum;

public class Test {
    private static final int TEST_ENCRYPT_BASE64_DECODE = 0;
    private static final int TEST_ENCRYPT_BASE64_ENCODE = TEST_ENCRYPT_BASE64_DECODE + 1;
    private static final int TEST_REFECT = TEST_ENCRYPT_BASE64_ENCODE + 1;
    private static final int TEST_ENCODEURL = TEST_REFECT + 1;

    public static void main(String[] args) {
//        Test thisCl = new Test();
//        thisCl.testLamabada();
//        LogUtil.printlnInConsle(System.currentTimeMillis());
//        int test_code = TEST_REFECT;
//        thisCl.processTest(test_code);
        try{
            throw new RuntimeException();
        }catch (Exception e){
            e.printStackTrace();
        }
        LogUtil.printlnInConsle("Excuted2");
    }

    private void processTest(int test_code) {
        switch (test_code) {
            case TEST_ENCRYPT_BASE64_DECODE:
                ArrayList<String> codeList = new ArrayList<>();
                codeList.add("eyJJc05lZWRDYXJkUmlzayI6dHJ1ZSwicGF5VHlwZUxpc3QiOjAsInN1YlBheVR5cGVMaXN0IjowfQ%3D%3D");
                JSONObject te = new JSONObject();

                codeList.add("eyJvaWQiOiIxMTEyNTE0IiwiYnVzdHlwZSI6MTEsInJlcXVlc3RpZCI6IjEwMTQwOTIyMTIwMDAwNzAwMDIiLCJpc2xvZ2luIjoxLCJmcm9tIjoiZmlsZTovL3dlYmFwcC90dWFuLyNib29raW5nIiwicmJhY2siOiJmaWxlOi8vd2ViYXBwL3R1YW4vI2Jvb2tpbmciLCJzYmFjayI6ImZpbGU6Ly93ZWJhcHAvdHVhbi8jYm9va2luZy5zdWNjZXNzITExMTI1MTQiLCJlYmFjayI6ImZpbGU6Ly93ZWJhcHAvdHVhbi8jYm9va2luZy5zdWNjZXNzITExMTI1MTQiLCJhdXRoIjoiMUVEOERDMzFDNjc5NDc4QkIzNzVEM0M1MEMxM0Q1MEFEM0Y3NkIwQ0FBRTQyM0I3NkUyQjI0RDJEOEZERjBFNiIsInRpdGxlIjoiMTDlhYPku6Pph5HliLgyNEvlm73pmYXov57plIHphZLlupfvvIjkuIrmtbfljZfkuqzkuJzot6%2FmraXooYzooZciLCJyZWNhbGwiOiJHcm91cC5Td2l0Y2guTFRQUGF5bWVudC5MVFBPcmRlclByb2Nlc3NXUyIsImFtb3VudCI6MSwiZXh0bm8iOiJncm91cDExMTI1MTQtMTAxNDA5MjIxNDAwMDAwMDcwMSIsIm5lZWRJbnZvaWNlIjpmYWxzZSwicGF5VHlwZUxpc3QiOjAsInN1YlBheVR5cGVMaXN0IjowfQ%3D%3D");
                if (codeList != null && codeList.size() > 0) {
                    for (String temp : codeList) {
                        String result = base64Decode(URLDecoder.decode(temp));
                        if (result != null) {
                            LogUtil.printlnInConsle("the result of base64 decode is:" + result);
                        }
                    }
                }
                break;
            case TEST_ENCRYPT_BASE64_ENCODE:
                break;
            case TEST_REFECT:
                Field[] fields = BasicBusinessTypeEnum.class.getFields();

                try {
                    Class enumClass = fields[10].getDeclaringClass();
                    enumClass.getMethods();
                    Method method_GetValue = enumClass.getMethod("getValue", null);
                    int value = (int) method_GetValue.invoke(BasicBusinessTypeEnum.valueOf(fields[10].getName()));
                    LogUtil.printlnInConsle("fields[0].getName() is:" + fields[10].getName());
                    LogUtil.printlnInConsle("fields[0].getType() is:" + fields[10].getType());
                    LogUtil.printlnInConsle("value) is:" + value);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

//                LogUtil.printlnInConsle("fields[0].getType() is:" + fields[0].getType());
                break;
            case TEST_ENCODEURL:
                String extend = FileUtil.readFile("D:/1.txt");
                System.out.println(encode(FileUtil.readFile("D:/1.txt")));
                break;
            default:
                LogUtil.printlnInConsle("Unrecoginze code");
        }
    }

    private void testLamabada() {
        ArrayList<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
//        a.forEach(temp -> {
//            System.out.println(temp+";");
//        });
        Iterator<String> ite=a.iterator();
        while (ite.hasNext()){
            ite.remove();
        }
//        a.forEach(System.out::println);
    }

    /**
     * Base64 decode this content
     *
     * @param code
     */
    private String base64Decode(String code) {
        String result = null;
        if (code != null && !"".equalsIgnoreCase(code)) {
            result = new String(Base64.decode(code.getBytes()));
        } else {
            LogUtil.printlnInConsle("base64Decode() received illegal param");
        }
        return result;
    }

    /**
     * Encode this code
     *
     * @param code
     */
    private String base64Encode(String code) {
        String result = null;
        if (code != null && !"".equalsIgnoreCase(code)) {
        } else {
            LogUtil.printlnInConsle("base64Encode() received illegal param");
        }
        return result;
    }

    private static String encode(String content) {
        String result = null;
        try {
            result = URLEncoder.encode(new String(Base64.encode(content.getBytes())), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
