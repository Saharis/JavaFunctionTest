package com.virgil.fileduplicate;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.poi.hwpf.usermodel.Fields;

public class Test3 {
	public static final String FOLD_PATH_1 = "D:\\workspaceJ2EE\\Ctrip_Business_Model\\src\\ctrip\\business\\intFlight\\model\\";
	public static final String FOLD_PATH_2 = "";

	public static void main(String[] args) {
		// Load
		try {
			String[] arguments = new String[] { "-d", FOLD_PATH_1, "FlightPassengerSeatModel.java" };
//			int result = com.sun.tools.javac.Main.compile(arguments);
//			System.out.println("result是"+result);
			URL classpath = new URL(FOLD_PATH_1);

			URLClassLoader classLoader = new URLClassLoader(new URL[] { classpath });

			Class<?> testClass = classLoader.loadClass("FlightPassengerSeatModel");
			Field[] list = testClass.getFields();
			for (Field temp : list) {
				System.out.println(temp.getName());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
