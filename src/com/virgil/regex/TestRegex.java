package com.virgil.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.virgil.util.LogUtil;

/**
 * Created by liuwujing on 15/2/5.
 */
public class TestRegex {
    public static void main(String[] args){
//        pattern("ssResult = BaseHelper.string2JSON","R.(?!.*(id))*\\.[0-9a-zA-Z\\_\\.]*");
//        pattern("R.s.x","R\\.[^i][^d]*\\.[0-9a-zA-Z\\_\\.]*");
//        pattern("R.s.x","R\\.[0-9a-zA-Z\\_\\.]*\\.[0-9a-zA-Z\\_\\.]*");
        pattern("R.s.x","\\..*\\.");
    }

    private static void pattern(String info,String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(info);
        StringBuilder sb=new StringBuilder("");
        while (m.find()){
            String pi=m.group();
            LogUtil.printlnInConsle("spice=" + pi);
            sb.append(pi);
        }
        LogUtil.printlnInConsle("matcher=" + m.matches());
        LogUtil.printlnInConsle("result=" + sb);
    }
}
