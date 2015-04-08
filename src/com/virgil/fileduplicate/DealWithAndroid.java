package com.virgil.fileduplicate;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.virgil.util.StringUtil;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DealWithAndroid {
	public static WritableWorkbook workbook;
	public static final String PATH_WRITE_DUP_NAME_FILE = "D:\\test.xls";
	public static final boolean IS_WRITE_DUP_TO_EXCEL = true;

	public static void main(String[] args) {

		// processDupNameFileList();
		// getClasses(Config.PATH_LIST_TEMP[0]);

		// getClasses(FlightAuthCodeSearchRequest.class.getPackage().getName());
		// try {
		// compareToClass(Class.forName("ctrip.business.flight.model.FlightAirportInformationModel"),
		// Class.forName("ctrip.business.flightCommon.model.FlightAirportInformationModel"));
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		Set<Class<?>> classList = getJarFile();
		writeDupFileToExcel(processDupWithClassType(classList));

	}

	private static ArrayList<String[]> processDupWithClassType(Set<Class<?>> classList) {
		Iterator<Class<?>> ite = classList.iterator();
		ArrayList<String[]> duList = new ArrayList<String[]>();
		HashMap<String, Class<?>> nameMap = new HashMap<String, Class<?>>();
		while (ite.hasNext()) {
			Class<?> cuClass = ite.next();
			String simpleName = cuClass.getSimpleName();
			if (nameMap.containsKey(simpleName)) {
				String compare;
				String content = compareToClass(cuClass, nameMap.get(simpleName));
				if (StringUtil.emptyOrNull(content)) {
					compare = "一致";
				} else {
					compare = "不一致";
				}
				duList.add(new String[] { simpleName, cuClass.getName(), nameMap.get(simpleName).getName(), compare, content });
			} else {
				nameMap.put(simpleName, cuClass);
			}
		}
		return duList;
	}

	private static String compareToClass(Class<?> a, Class<?> b) {
		Field[] fiedList_a = a.getDeclaredFields();
		Field[] fiedList_b = b.getDeclaredFields();
		boolean compare = true;
		StringBuilder difContent = new StringBuilder();
		if (fiedList_a.length == fiedList_b.length) {
			int lenth = fiedList_a.length;
			for (int i = 0; i < lenth; i++) {
				boolean foundU = false;
				for (int j = 0; j < lenth && !foundU; j++) {
					if (fiedList_a[i].getName().equals(fiedList_b[j].getName())) {
						if (fiedList_a[i].getType().equals(fiedList_b[j].getType())) {
							StringBuilder temp=compareAnnotion(fiedList_a[i], fiedList_b[j],new StringBuilder());
							if(StringUtil.emptyOrNull(temp.toString())){
								foundU = true;
								continue;
							}else{
								foundU = false;
								difContent.append(temp);
								continue;
							}
						} else {
							foundU = false;
							difContent.append("属性类型不一致：\r\n字段名称是：" + fiedList_a[i].getName() + "\r\n字段类型是：" + fiedList_a[i].getType().getName() + "-" + fiedList_b[j].getType().getName() + "");
							continue;
						}

					} else {
						foundU = false;
					}
				}
				if (!foundU) {
					if (StringUtil.emptyOrNull(difContent.toString())) {
						difContent.append("属性名称不一致：\r\n" + a.getName() + "的" + fiedList_a[i].getName() + "字段，在" + b.getName() + "中没有找到");
					}
					compare = false;
					break;
				} else {
					continue;
				}
			}
		} else {
			compare = false;
			difContent.append("两个类的字段数量不一致");
		}
		if (!compare) {
			if (StringUtil.emptyOrNull(difContent.toString())) {
				difContent.append("属性不一致");
			}
		}

		return difContent.toString();
	}

	private static StringBuilder compareAnnotion(Field field_a, Field field_b,StringBuilder string_content) {
//		SerializeField annotation_a = field_a.getAnnotation(SerializeField.class);
//		SerializeField annotation_b = field_b.getAnnotation(SerializeField.class);
//		if (!annotation_a.type().equals(annotation_b.type())) {
//			string_content.append("type不一致");
//		}
//		if (annotation_a.length() != annotation_b.length()) {
//			string_content.append("length不一致");
//		}
//		if (annotation_a.index() != (annotation_b.index())) {
//			string_content.append("index不一致");
//		}
//		if (annotation_a.require() != (annotation_b.require())) {
//			string_content.append("requre不一致");
//		}
//		if (annotation_a.isPriceField() != (annotation_b.isPriceField())) {
//			string_content.append("isPriceField不一致");
//		}
//		if (!annotation_a.serverType().equals(annotation_b.serverType())) {
//			string_content.append("serverType不一致");
//		}
//		if (!annotation_a.metadata().equals(annotation_b.metadata())) {
//			string_content.append("metadata不一致");
//		}
//		if (!annotation_a.format().equals(annotation_b.format())) {
//			string_content.append("format不一致");
//		}
		StringBuilder temp=new StringBuilder();
		if(!StringUtil.emptyOrNull(string_content.toString())){
			temp.append("Annotation不一致：\r\n 字段"+field_a.getName()+"：");
			temp.append(string_content);
		}
		return temp;
	}

	private static Set<Class<?>> getJarFile() {
		JarFile jfile;
		Set<Class<?>> classList = new LinkedHashSet<Class<?>>();
		try {
			jfile = new JarFile("D:\\workspaceJ2EE\\FunctionTest\\lib\\business_model.jar");
			Enumeration files = jfile.entries();
			String packageName = null;
			while (files.hasMoreElements()) {
				JarEntry entry = (JarEntry) files.nextElement();

				// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
				String name = entry.getName();
				// 如果是以/开头的
				if (name.charAt(0) == '/') {
					// 获取后面的字符串
					name = name.substring(1);
				}
				// 如果前半部分和定义的包名相同
				if (name.startsWith("ctrip/business/flight") || name.startsWith("ctrip/business/intFlight")) {
					int idx = name.lastIndexOf('/');
					// 如果以"/"结尾 是一个包
					if (idx != -1) {
						// 获取包名 把"/"替换成"."
						packageName = name.substring(0, idx).replace('/', '.');
					}
					// 如果可以迭代下去 并且是一个包
					if ((idx != -1) || true) {
						// 如果是一个.class文件 而且不是目录
						if (name.endsWith(".class") && !entry.isDirectory()) {
							// 去掉后面的".class" 获取真正的类名
							String className = name.substring(packageName.length() + 1, name.length() - 6);
							try {
								// 添加到classes
								classList.add(Class.forName(packageName + "." + className));
							} catch (ClassNotFoundException e) {
								// log
								// .error("添加用户自定义视图类错误 找不到此类的.class文件");
								e.printStackTrace();
							}
						}
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classList;

	}

	

	/**
	 * 将所有重名类的信息写入到Excel
	 * 
	 * @param duList
	 */
	private static void writeDupFileToExcel(ArrayList<String[]> duList) {
		if (IS_WRITE_DUP_TO_EXCEL) {
			try {
				workbook = Workbook.createWorkbook(new File(PATH_WRITE_DUP_NAME_FILE));
				WritableSheet summarySheet = workbook.createSheet("重名信息", 0);
				summarySheet.addCell(new Label(0, 0, "文件名"));
				summarySheet.addCell(new Label(1, 0, "是否完全一致"));
				summarySheet.addCell(new Label(2, 0, "不一致的内容是"));
				summarySheet.addCell(new Label(3, 0, "路径1"));
				summarySheet.addCell(new Label(4, 0, "路径2"));
				int startRange = 0;
				int startColumn = 0;
				for (String[] temp : duList) {
					startRange++;
					startColumn = 0;
					summarySheet.addCell(new Label(startColumn++, startRange, temp[0]));
					summarySheet.addCell(new Label(startColumn++, startRange, temp[3]));
					summarySheet.addCell(new Label(startColumn++, startRange, temp[4]));
					summarySheet.addCell(new Label(startColumn++, startRange, temp[1]));
					summarySheet.addCell(new Label(startColumn++, startRange, temp[2]));
				}
				workbook.write();
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					System.err.println("jar类型的扫描");
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

}
