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
    public static <T> T transObjectToT(Object object, Class<T> classT) {
        T tTemp=null;
        if(object!=null && classT.isInstance(object)){
            tTemp=classT.cast(object);
        }
        return tTemp;
    }
}
