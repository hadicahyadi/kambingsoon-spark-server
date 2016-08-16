/**
 * 
 */
package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author hadi
 *
 * Aug 16, 2016
 */
public interface GenericMapper<T> {

	public T mapRow(ResultSet rs, int index) throws SQLException;
}
