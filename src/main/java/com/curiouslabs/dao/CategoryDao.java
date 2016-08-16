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

import com.curiouslabs.model.Category;
import com.curiouslabs.util.Datasource;
import com.curiouslabs.util.StateBeanProcessor;

/**
 * @author hadi
 *
 *         Aug 15, 2016
 */
public class CategoryDao implements GenericDao<Category> {

	private QueryRunner run;

	public CategoryDao() {
		run = new QueryRunner(Datasource.getConnection());
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see com.curiouslabs.dao.GenericDao#save(java.lang.Object)
	 */
	@Override
	public Category save(Category object) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see com.curiouslabs.dao.GenericDao#getAll()
	 */
	@Override
	public List<Category> getAll() {
		// QueryRunner run = new QueryRunner(Datasource.getConnection());
		String sql = "select * from category";
		
		List<Category> list = null;
		List results = null;
		try {
			results = (List) run.query(sql,new MapListHandler());
			list = results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;

	}
	
	public List getall() {
		
		String sql = "select * from category";
		
		List results = new ArrayList<>();
		try {
			results = (List) run.query(sql,new MapListHandler());
			for(int i=0;i<results.size();i++){
				System.out.println(results.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;

	}

}
