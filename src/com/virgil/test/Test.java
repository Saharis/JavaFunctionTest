package com.virgil.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.xmlbeans.impl.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.virgil.mail.FunctionEntrance;
import com.virgil.util.FileUtil;
import com.virgil.util.LogUtil;
import com.virgil.util.StringUtil;

public class Test {
    private static final int TEST_ENCRYPT_BASE64_DECODE = 0;
    private static final int TEST_ENCRYPT_BASE64_ENCODE = TEST_ENCRYPT_BASE64_DECODE + 1;
    private static final int TEST_REFECT = TEST_ENCRYPT_BASE64_ENCODE + 1;
    private static final int TEST_ENCODEURL = TEST_REFECT + 1;
    private static String strST="static";
    private String str="norma";
private FunctionEntrance mFunctionEntrance=new FunctionEntrance();
    public static void main(String[] args) {
//        Test thisCl = new Test();
//        thisCl.testLamabada();
//        LogUtil.printlnInConsle(System.currentTimeMillis());
//        int test_code = TEST_REFECT;
//        thisCl.processTest(test_code);
//        try{
//            throw new RuntimeException();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        LogUtil.printlnInConsle(~10);
//
//        getAPKSigInfo("/Users/liuwujing/Downloads/root_explorer_3.2.apk");
//        LogUtil.printlnInConsle(Integer.toBinaryString(9));
//        System.out.println("".replaceAll(".{4}(?!$)", "$0 "));
//        LogUtil.printlnInConsle(String.format("%,c","4392268300009140"));
//        binarytest();
//        String a="1234567";
//        a.contains("12");
//        ;a.regionMatches(0,"a12sd12fas1",5,2);
//        String url="file:////webapp/topshop/webapp/index.html#payshop?shoptype=2&restaurantid=20000248&type=1&precent=20&from_native_page=1&isfrompayment=1";
//        int length="webapp/".length();
//        int index=url.indexOf("webapp/");
//        int lastIndex=url.lastIndexOf("webapp/");
//        LogUtil.printlnInConsle("length-"+length+";index-"+index+";lastIndex-"+lastIndex);
//        File file =new File("/Users/liuwujing/Downloads/");
//        getFileNameList("/Users/liuwujing/Downloads/","");
        //正则表达式
//        Pattern p= Pattern.compile("^PAY_LOG_TAG.*");
//        Matcher m=p.matcher("PAY_LOG_TAG_2014-12-10-11.txt");
//        LogUtil.printlnInConsle(m.matches()+"");
//        String s=" 1 2 3 ";
//        LogUtil.printlnInConsle(s);
//        LogUtil.printlnInConsle(s.trim());
//        String urlsubFix="file://webapp/tuan/index.html#booking.success!1112996";
//        if (urlsubFix.contains("#")) {
//            urlsubFix = "/index.html" +urlsubFix.substring(urlsubFix.indexOf("#"), urlsubFix.length());
//        }
//        String teturl="file
        LogUtil.printlnInConsle(buildURL("file://webapp/tuan/index.html#booking.success!1112996"));
//        Test s=new Test();
//        Son son=new Son();
//        Class a=Test.class;
//        try {
//            Field strFN=s.getClass().getDeclaredField("strST");
//            strFN.setAccessible(true);
//            LogUtil.printlnInConsle("strFN.get(s)="+strFN.get(s));
//            Field strFS=a.getDeclaredField("strST");
//            strFS.setAccessible(true);
//            LogUtil.printlnInConsle("strFS.get(a)="+strFS.get(a));
//
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }catch (IllegalAccessException e){
//
//        }
//        try {
//            Field field=son.getClass().getDeclaredFields()[0];
//            field.setAccessible(true);
//            Class type=field.getType();
//            AbstractFather af1=(AbstractFather)field.get(son);
//            Field[] fields=af1.getClass().getSuperclass().getDeclaredFields();
//            fields[0].setAccessible(true);
//            fields[0].get(af1);
//            if(NormalFather.class.isAssignableFrom(type)){
//                NormalFather nf=(NormalFather)field.get(son);
//                LogUtil.printlnInConsle(nf.getClass().getDeclaredFields().toString());
//            }
//            if(AbstractFather.class.isAssignableFrom(type)){
//                AbstractFather af=(AbstractFather)field.get(son);
//                LogUtil.printlnInConsle(af.getClass().getDeclaredFields().toString());
//            }
//
//            if(InterfaceFather.class.isAssignableFrom(type)){
//                InterfaceFather ifa=(InterfaceFather)field.get(son);                ifa.getClass().getInterfaces()[0].getDeclaredFields();
//                LogUtil.printlnInConsle(ifa.getClass().getDeclaredFields().toString());
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        String str="JAVA";
//        StringBuilder sb=new StringBuilder("JAVA");
//        StringBuffer sf=new StringBuffer("JAVA");
//        LogUtil.printlnInConsle(String.join("-\n", ZoneId.getAvailableZoneIds()));
//        ArrayList<NormalFather> list=new ArrayList<NormalFather>();
//        NormalFather nf=new NormalFather();
//        AbstractFather af=nf;
//        nf=(NormalFather)af;
//        str.join()
//        ReflectUtil.getAllFieldsWithClassName(s.getClass().getName());
//        String response="ctrip.business.other.OtherPasswordChangeResponse";
//        int lastIndext=response.lastIndexOf("Response");
//        String request=response.substring(0,lastIndext)+"Request";
//        LogUtil.printlnInConsle(request);
    }

    public static String buildURL(String url) {
        String finalPath = null;
        if (!StringUtil.emptyOrNull(url)) {
            //下面的逻辑，例如：prefix/webapp/BU/content#subfix
            int prefixLength = "webapp/".length();
            int prefixPosition = url.indexOf("webapp/");
            if (prefixLength >= 0 && prefixPosition >= 0) {
                int lastPOIndex = url.substring(prefixLength + prefixPosition).indexOf("/");
                //将路径中的"webapp"替换为本地资源路径
                String path = url.substring(prefixLength + prefixPosition, prefixLength + prefixPosition + lastPOIndex);
                String urlsubFix = url.substring(prefixLength + prefixPosition + lastPOIndex, url.length());
                if (urlsubFix.contains("#")) {
                    urlsubFix = "/index.html" +urlsubFix.substring(urlsubFix.indexOf("#"), urlsubFix.length());
                }
                finalPath = "file:///data/data/ctrip.android.view/app_ctripwebapp" + path +  urlsubFix;
            } else {
                return null;
            }
        }

        return finalPath;
    }
    private static String[] getFileNameList(String folderPath,final String fileNameMatcher){
        File file =new File(folderPath);
        FilenameFilter filenameFilter=new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                Pattern p= Pattern.compile(fileNameMatcher);
                Matcher m=p.matcher(name);
                return m.matches();
            }
        };
        return file.list(filenameFilter);
    }

    public static String getAPKSigInfo(String filePath) {
        String subjectDN = "";
        String issuerDN = "";
        String publicKey = "";
        try {
            JarFile jarFile = new JarFile(filePath);
            JarEntry jarEntry = jarFile.getJarEntry("AndroidManifest.xml");
            if (jarEntry != null) {
                byte[] readBuffer = new byte[8192];
                InputStream is = new BufferedInputStream(jarFile.getInputStream(jarEntry));
                while (is.read(readBuffer, 0, readBuffer.length) != -1) {
                    // not using
                }
                Certificate[] certs = jarEntry.getCertificates();
                if(certs!=null && certs.length>0)
                {
                    //获取证书
                    X509Certificate x509cert = (X509Certificate) certs[0];
                    //获取证书发行者
                    issuerDN = x509cert.getIssuerDN().toString();
                    //获取证书所有者
                    subjectDN = x509cert.getSubjectDN().toString();
                    //证书key
                    publicKey = x509cert.getPublicKey().toString();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
private static void binarytest(){

    String binary[] = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    int a = 3; // 0 + 2 + 1 or 0011 in binary
    int b = 6; // 4 + 2 + 0 or 0110 in binary
    int c = a | b;
    int d = a & b;
    int e = a ^ b;
    int f = (~a & b) | (a & ~b);
    int g = ~a & 0x0f;


    System.out.println(" a = " + binary[a]);
    System.out.println(" b = " + binary[b]);
    System.out.println(" a|b = " + binary[c]);
    System.out.println(" a&b = " + binary[d]);
    System.out.println(" a^b = " + binary[e]);
    System.out.println("~a&b|a&~b = " + binary[f]);
    System.out.println(" ~a = " + binary[g]);
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
//                Field[] fields = BasicBusinessTypeEnum.class.getFields();
//
//                try {
//                    Class enumClass = fields[10].getDeclaringClass();
//                    enumClass.getMethods();
//                    Method method_GetValue = enumClass.getMethod("getValue", null);
//                    int value = (int) method_GetValue.invoke(BasicBusinessTypeEnum.valueOf(fields[10].getName()));
//                    LogUtil.printlnInConsle("fields[0].getName() is:" + fields[10].getName());
//                    LogUtil.printlnInConsle("fields[0].getType() is:" + fields[10].getType());
//                    LogUtil.printlnInConsle("value) is:" + value);
//
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//
////                LogUtil.printlnInConsle("fields[0].getType() is:" + fields[0].getType());
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

    private static void teststai(){
        LogUtil.printlnInConsle(Test.strST);
    }
    /**
     * Base64 decode this content
     *
     * @param code
     */
    private String base64Decode(String code) {
        this.testLamabada();
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
