package com.virgil.reflect;

import java.lang.reflect.Field;

/**
 * Created by liuwujing on 14-9-23.
 */
public class ReflectUtil {

    public static Field[] getAllFieldsWithClassName(String className){
        try {
            Class reconClass=Class.forName(className);

            if(reconClass!=null){
                Field[] fiedls=reconClass.getDeclaredFields();
                return fiedls;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
