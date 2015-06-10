package com.virgil.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.http.util.TextUtils;

public class FileUtil {

    /**
     * Delete all files within File
     * @param file File
     */
	public static void deleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                deleteFile(f);
            }
            file.delete();
        }
    }
    public static void deleteAllFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            deleteAllFile(new File(path));
        }
    }

    public static void deleteAllFile(File file) {
        if (file != null) {
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    File[] sonList = file.listFiles();
                    if (sonList != null && sonList.length > 0) {
                        for (File sonPath : sonList) {
                            deleteAllFile(sonPath);
                        }
                    }
                    file.delete();
                }
            }
        }
    }
    /**
     * Write Data to File
     * @param data String
     * @param filePath String
     * @param isAppend boolean
     */
	public static void writeToFile(String data, String filePath, boolean isAppend) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, isAppend)));
			out.write(data + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    /**
     * Read File From Path
     * @param fileName String
     * @return String
     */
    public static String readFileContent(String fileName){
        String str=null;
        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new FileReader(fileName));
            str=reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    /**
     * 读取Properties
     * @param path FileInfo
     * @return Properties
     */
    public static Properties readProperties(String path) {
        Properties pro = null;
        InputStream in = null;
        try {
            in = new FileInputStream(path);
            pro = new Properties();
            pro.load(in);

        } catch (IOException e) {
            pro = null;
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pro;
    }

    /**
     * Write Properties To File
     * @param pro Properties
     * @param path String
     */
    public static void writeProperties(Properties pro,String path){
        if(pro!=null&&!StringUtil.emptyOrNull(path)){
            OutputStream out=null;
            try {
                out =new FileOutputStream(path);
                pro.store(out,"");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(out!=null){
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
