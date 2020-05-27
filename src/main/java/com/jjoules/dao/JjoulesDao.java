/**
 * 
 */
package com.jjoules.dao;

/**
 * @author sanoussy
 *
 */
public class JjoulesDao {
	
	private static final JjoulesDao INSTANCE = new JjoulesDao();

	/**
	 * 
	 */
	private JjoulesDao() {
	}
	
	public static JjoulesDao getInstance() {
		return INSTANCE;
	}
}
