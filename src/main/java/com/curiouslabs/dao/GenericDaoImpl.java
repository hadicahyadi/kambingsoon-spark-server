package com.curiouslabs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.curiouslabs.model.BaseModel;
import com.curiouslabs.model.BaseModelInterface;
import com.curiouslabs.util.Datasource;

public class GenericDaoImpl<T extends BaseModel> {
	
	public String insertSql;
	public String updateSql;
	public String deleteSql;
	public String selectSql;
	public ResultSetHandler<T> rs;
	
	private QueryRunner run;
	
	public GenericDaoImpl(String tableName){
		run = new QueryRunner(Datasource.getConnection());
		selectSql = "select * from "+tableName;
	}
	
	public List<T> getAll() throws SQLException{
		T object = (T) new Object();
		ResultSetHandler rs = new BeanListHandler<>(object.getClass());
		List<T> list = (List<T>) run.query(selectSql, rs);
		return list;
	}

//	public Long save(T object) throws SQLException{
//		Long id = run.insert(insertSql, new ScalarHandler<Long>(),object.getClass);
//		return id;
//	}
}
