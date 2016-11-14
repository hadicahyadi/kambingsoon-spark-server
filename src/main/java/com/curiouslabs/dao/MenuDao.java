/**
 * 
 */
package com.curiouslabs.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.curiouslabs.dao.beanprocessor.MenuBeanProcessor;
import com.curiouslabs.dao.mapper.ConfigMenuMapper;
import com.curiouslabs.dao.mapper.MenuMapper;
import com.curiouslabs.model.ConfigMenu;
import com.curiouslabs.model.Menu;
import com.curiouslabs.util.Datasource;

/**
 * @author Dio
 *
 * Aug 16, 2016
 */
public class MenuDao implements GenericDao<Menu>{
	
	private QueryRunner run;
	
	public MenuDao(){
		run = new QueryRunner(Datasource.getConnection());
	}
	
	public List<Menu> getAllParentMenu() throws SQLException {
		String sql = "select m.*,c.* from menu m join category c on m.category_id = c.id"
				+ " where m.parent_id is null";
		List<Menu> result = run.query(sql, new BeanListHandler<Menu>(Menu.class,new MenuBeanProcessor()));
		return result;
	}
	
	public List<Menu> getParentMenu(Long categoryId) throws SQLException {
		String sql = "select m.*,c.* from menu m join category c on m.category_id = c.id where m.parent_id is null"
				+ " and category_id = "+categoryId;
		List<Menu> result = run.query(sql, new BeanListHandler<Menu>(Menu.class,new MenuBeanProcessor()));
		return result;
	}

	@Override
	public Long save(Menu object) throws SQLException {
		String sql = "insert into menu(parent_id,menu_name,image_url,price,description,category_id) "
				+ "values(?,?,?,?,?,?)";
		Long id = run.insert(sql, new ScalarHandler<Long>(),object.toArray()); 
		return id;
	}

	@Override
	public int update(Menu obhect) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<Menu> getAll() throws SQLException {
		String sql = "select m.*,c.* from menu m join category c on m.category_id = c.id where m.parent_id is not null";
		List<Menu> result = run.query(sql, new BeanListHandler<Menu>(Menu.class,new MenuBeanProcessor()));
		return result;
	}
	
	public List<Menu> getByParent(Long parentId) throws SQLException{
		String sql = "select m.*,c.* from menu m "
				+ "join category c on m.category_id = c.id "
				+ "where m.parent_id = "+parentId;
		List<Menu> result = run.query(sql, new BeanListHandler<Menu>(Menu.class,new MenuBeanProcessor()));
		return result;
	}
	
	public static void main(String argsp[]) throws SQLException{
//		Menu menu = new Menu();
//		menu.setCategoryId(1l);
//		menu.setMenuName("test menu");
//		menu.setParentId(1l);
//		menu.setPrice(new BigDecimal("12000"));
//		menu.setDescription("this is test menu");
//		menu.setImageUrl("http://sfwr.com/fislg");
//		Long id = new MenuDao().save(menu);
//		System.out.println(new MenuDao().getParentMenu().size());
	}

}
