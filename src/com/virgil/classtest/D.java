package com.virgil.classtest;

public class D extends C {
	public String tt = "ttOut";

	@Override
	public void freshtt() {
		System.out.println("执行到了子类freshtt");
		System.out.println("执行到了子类freshtt-一开始-tt="+tt);
		tt = "freshtt";
		System.out.println("执行到了子类freshtt-赋值后-tt="+tt+";tt.hashCode()="+tt.hashCode());
	}
	
}
