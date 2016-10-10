/**
 * 
 */
package com.curiouslabs.dao;

import java.util.List;

/**
 * @author hadi
 *
 * Aug 15, 2016
 */
public interface GenericDao<T> {
	
	public int save(T object);
	
	public List<T> getAll();

}
