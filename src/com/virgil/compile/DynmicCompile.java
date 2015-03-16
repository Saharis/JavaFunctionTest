package com.virgil.compile;

public class DynmicCompile {
//	public static final String FOLD_PATH_1 = "D:/workspaceJ2EE/Ctrip_Business_Model/src/ctrip/business/intFlight/model/";
	public static final String FOLD_PATH_1 = "D:/path/";

	public static void main(String[] args) {
		test();
	}

	public static void test(){
		String filename = "FlightPassengerSeatModel.java";
	    String[] args = new String[] {FOLD_PATH_1+filename };
//	    int status = com.sun.tools.javac.Main.compile(args);
//	    System.out.println("编译结果是"+status);
	}
}
