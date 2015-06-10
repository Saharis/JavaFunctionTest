package com.virgil.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.virgil.util.ClassUtil;
import javafx.util.Pair;

/**
 * UpdateModel
 * Created by liuwujing on 15/6/8.
 */
public class UpdateModel {

    public static void main(String[] args) {
        UpdateModel up = new UpdateModel();
//        List<Class> list=up.filterFileList("com.ctrip.pay.base.businessmodel");
        HashMap<String, List<Pair<String, Class>>> classInfo = up.filterFileList("com.ctrip.pay.base.businessmodel");
        List<String> sqlList=up.getExcuteSql(true,classInfo);
        DBUtil.deleteAllTable();
        DBUtil.updateTable(sqlList);
    }

    public HashMap<String, List<Pair<String, Class>>> filterFileList(String packageName) {
        Set<String> classNames = ClassUtil.getClassName("com.ctrip.pay.base.businessmodel", true);
        HashMap<String, List<Pair<String, Class>>> classCollect = null;
        try {
            if (classNames != null && classNames.size() > 0) {
                classCollect = new HashMap<>();
                for (String name : classNames) {
                    if (name.endsWith("Entity")) {
                        continue;
                    } else {
                        Class clz = Class.forName(name);
                        getFieldInfo(clz, classCollect);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classCollect;
    }

    public List<Class> getClassList(Class parnet, List<Class> classList) {
        if (parnet != null) {
            if (classList == null) {
                classList = new ArrayList<>();
            }
            Field[] files = parnet.getDeclaredFields();
            if (files != null && files.length > 0) {
                for (Field field : files) {
                    Type gType = field.getGenericType();

                    if (gType == String.class) {

                    }
                    ParameterizedType pType = (ParameterizedType) gType;
                    Class typeC = (Class) pType.getActualTypeArguments()[0];
                }
            }
        }
        return classList;
    }


    public HashMap<String, List<Pair<String, Class>>> getFieldInfo(Class parentClass, HashMap<String, List<Pair<String, Class>>> fieldMap) {
        if (parentClass != null) {
            if (fieldMap == null) {
                fieldMap = new HashMap<>();
            }
            if (!fieldMap.containsKey(parentClass.getSimpleName())) {
                Field[] fields = parentClass.getDeclaredFields();
                if (fields != null && fields.length > 0) {
                    List<Pair<String, Class>> valueList = new ArrayList<>();
                    for (Field field : fields) {
                        Type gType=field.getGenericType();
                        if(gType instanceof ParameterizedType){
                            ParameterizedType pType = (ParameterizedType) gType;
                            valueList.add(new Pair<>(field.getName(), (Class) pType.getActualTypeArguments()[0]));
                        }else{
                            valueList.add(new Pair<>(field.getName(), (Class)field.getGenericType()));
                        }

//                        if(field.getGenericType())
                    }
                    fieldMap.put(parentClass.getSimpleName(), valueList);
                }
            }
        }
        return fieldMap;
    }

    public List<String> getExcuteSql(boolean freashNew,HashMap<String, List<Pair<String, Class>>> classMap) {
        if(freashNew){
            return generateFreshNewTable(classMap);
        }else{
            return getOldTableInfo(classMap);
        }
    }

    public List<String> getOldTableInfo(HashMap<String, List<Pair<String, Class>>> classMap) {
        return null;
    }

    public List<String> generateFreshNewTable(HashMap<String, List<Pair<String, Class>>> classMap) {
        List<String> sqlList = null;
        if (classMap != null && !classMap.isEmpty()) {
            Iterator<Map.Entry<String, List<Pair<String, Class>>>> set = classMap.entrySet().iterator();
            sqlList = new ArrayList<>();
            while (set.hasNext()) {
                Map.Entry<String, List<Pair<String, Class>>> entry = set.next();
                String key = entry.getKey();
                List<Pair<String, Class>> value = entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append("CREATE TABLE");
                sb.append(" ");
                sb.append("`");
                sb.append(key);
                sb.append("`");
                sb.append(" ");
                sb.append("(");
                sb.append("`pk` INT(11) NOT NULL AUTO_INCREMENT,");
                for (Pair<String, Class> temp : value) {
                    sb.append("`");
                    sb.append(temp.getKey());
                    sb.append("`");
                    sb.append(" ");
                    if(temp.getValue().isPrimitive()){

                        if(temp.getValue() ==int.class) {
                            sb.append(temp.getValue().getSimpleName());
                            sb.append("(10)  NULL DEFAULT '0',");
                        }else if(temp.getValue() ==boolean.class){
                            sb.append(temp.getValue().getSimpleName());
                            sb.append("(1)  NULL DEFAULT '0',");
                        }else if(temp.getValue() ==long.class){
                            sb.append("DOUBLE  NULL DEFAULT '0',");
                        }else{
                            sb.append(temp.getValue().getSimpleName());
                            sb.append("NULL DEFAULT '0',");
                        }
                    }else{
                        sb.append("TEXT");
                        sb.append(" NULL COLLATE 'utf8_general_ci',");
                    }

                }

                sb.append("PRIMARY KEY (`pk`)");
                sb.append(")");
                sqlList.add(sb.toString());
            }
        }
        return sqlList;
    }



}
