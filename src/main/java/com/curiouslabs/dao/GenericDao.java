/**
 * 
 */
package com.curiouslabs.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hadi
 *
 * Aug 15, 2016
 */
public interface GenericDao<T> {
	
	public Long save(T object) throws SQLException;
	
	public int update(T obhect) throws SQLException;
	
	public List<T> getAll() throws SQLException;

}
