package com.virgil.excel.model;

import java.util.ArrayList;

public class FieldBreakStack {

	public ArrayList<StackItem> stack = null;

	public FieldBreakStack() {
		stack = new ArrayList<StackItem>();
	}

	public void push(StackItem item) {
		if (stack == null) {
			stack = new ArrayList<StackItem>();
		}
		stack.add(item);
	}

	public void pop() {
		if (stack != null && stack.size() > 0) {
			stack.remove(stack.size() - 1);
		} else {
			stack = new ArrayList<StackItem>();
		}
	}

	public int size() {
		if (stack == null) {
			stack = new ArrayList<StackItem>();
		}
		return stack.size();
	}

	public boolean isEmpty() {
		boolean isEmpty=false;
		if(stack==null){
			isEmpty=true;
		}else{
			if(stack.size()==0){
				isEmpty=true;
			}else{
				isEmpty=false;
			}
		}
		return isEmpty;
	}

	public StackItem get() {
		if (stack != null && stack.size() > 0) {
			return stack.get(stack.size() - 1);
		} else {
			return null;
		}
	}

	public void clear() {
		stack = null;
	}
}
