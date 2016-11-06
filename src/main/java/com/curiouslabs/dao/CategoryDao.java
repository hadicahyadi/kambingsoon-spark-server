/**
 * 
 */
package com.curiouslabs.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.curiouslabs.dao.beanprocessor.CategoryBeanProcessor;
import com.curiouslabs.model.Category;
import com.curiouslabs.util.Datasource;
import com.curiouslabs.util.StateBeanProcessor;


public class CategoryDao implements GenericDao<Category> {

	private QueryRunner run;

	public CategoryDao() {
		run = new QueryRunner(Datasource.getConnection());
	}

	@Override
	public Long save(Category object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Category> getAll() throws SQLException {
		String sql = "select * from category";
		List<Category> result = run.query(sql, new BeanListHandler<Category>(Category.class,new CategoryBeanProcessor()));
		return result;
	}

	@Override
	public int update(Category obhect) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String args[]) throws SQLException{
		System.out.println(new CategoryDao().getAll().size());
	}
	

}
