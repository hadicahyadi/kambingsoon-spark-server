package com.curiouslabs.dao.beanprocessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;

import com.curiouslabs.dao.mapper.GenericRowMapper;
import com.curiouslabs.dao.mapper.WaitingListMapper;

public class GenericBeanProcessor<T> extends BasicRowProcessor {

	private GenericRowMapper rowMapper;

	public GenericBeanProcessor(GenericRowMapper rowMapper) {
//		this.object = object;
		this.rowMapper = rowMapper;
	}

	@Override
	public <T> List<T> toBeanList(ResultSet rs, Class<T> type) throws SQLException {
		
		List<T> list = new LinkedList<>();
		while (rs.next()) {
			int index = 0;
			T object = (T) rowMapper.mapRow(rs, index);
			list.add(object);
		}
		return list;
	}
	
	@Override
	public <T> T toBean(ResultSet rs, Class<T> type) throws SQLException {
		while(rs.next()){
			int index = 0;
			T objectResult = (T) rowMapper.mapRow(rs, index);			
			return objectResult;
			
		}
		return null;
	}
}
