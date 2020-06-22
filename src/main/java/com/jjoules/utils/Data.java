/**
 * 
 */
package com.jjoules.utils;

import java.util.List;

/**
 * @author sanoussy
 *
 */
public class Data {
	
	private String className;
	private List<Result> methods;
	/**
	 * 
	 */
	public Data(String className, List<Result> methods) {
		this.className = className;
		this.methods = methods;
	}
	
	public List<Result> getMethods(){
		return this.methods;
	}
	
	public String getClassName() {
		return this.className;
	}
	
	
}
