package com.virgil.compile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicCompileTest {
	public static void main(String[] args) {
		try {
			String className = "Runtime";
			String classDir = System.getProperty("user.dir")+"/CtripBusinesModel";
			File file = new File(classDir, className + ".java");
			PrintWriter out = new PrintWriter(new FileOutputStream(file));
			// 代码
			StringBuffer sbf = new StringBuffer(128);
			sbf.append("public class ");
			sbf.append(className);
			sbf.append("{");
			sbf.append("public void hello () {");
			sbf.append("System.out.println(\"DynamicCompile Success.\");");
			sbf.append("}");
			sbf.append("}");
			String code = sbf.toString();
			out.println(code);
			out.flush();
//			out.close();
			File fil2e = new File(classDir, "FlightSeatEntityModel.java");
			// 编译
//			int result=com.sun.tools.javac.Main.compile(new String[] { "-d", classDir,file.getName()});// JAVA_HOME/jdk/lib/tools.jar
//			System.out.println(result);
//			URL url = new URL("file:/" + classDir + File.separator);
//			// 动态加载/执行
//			URLClassLoader loader = new URLClassLoader(new URL[] { url });
//			Class<?> clazz = loader.loadClass(className);
//			Object obj = clazz.newInstance();
//			Method method = clazz.getMethod("hello");
//			method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

