package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GenericRowMapper<T> {
	
	public T mapRow(ResultSet rs,int index) throws SQLException;

}
