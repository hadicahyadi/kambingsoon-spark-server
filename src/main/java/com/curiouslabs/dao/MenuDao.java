/**
 * 
 */
package com.curiouslabs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.curiouslabs.dao.mapper.ConfigMenuMapper;
import com.curiouslabs.dao.mapper.MenuMapper;
import com.curiouslabs.model.ConfigMenu;
import com.curiouslabs.model.Menu;
import com.curiouslabs.util.Datasource;

/**
 * @author hadi
 *
 * Aug 16, 2016
 */
public class MenuDao{
	
	private QueryRunner run;
	
	public MenuDao(){
		run = new QueryRunner(Datasource.getConnection());
	}
	
	public List getParentMenu() {

		String sql = "select * from menu where parent_id is null";

		List results = new ArrayList<>();
		try {
			results = (List) run.query(sql, new MapListHandler());
			for (int i = 0; i < results.size(); i++) {
				System.out.println(results.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	public List getChildMenu(int parentId){
		String query = "select * from menu m "
				+ " where m.parent_id ="+parentId;

		List<Menu> results = new ArrayList<Menu>();
		
		try {
			
			//--- Run first query to set one relation object
			run.query(query, new ResultSetHandler<List<Menu>>(){

				@Override
				public List<Menu> handle(ResultSet rs) throws SQLException {
					int index = 0;
					Menu menu;
					ConfigMenu cm;
					while(rs.next()){
						menu = new MenuMapper().mapRow(rs, index);
						results.add(menu);
					}
					
					return results;
				}
			});
			
			//--- Add second query for many relation object to one object
			for(Menu m : results){
				String configQuery = "select * from config_menu where menu_id = "+m.getId();
				run.query(configQuery, new ResultSetHandler<List<Menu>>(){
					List<ConfigMenu> configResults = new ArrayList<ConfigMenu>();
					@Override
					public List<Menu> handle(ResultSet rs) throws SQLException {
						int index = 0;
						while(rs.next()){
							ConfigMenu cm = new ConfigMenuMapper().mapRow(rs, index);
							configResults.add(cm);
						}
						
						m.setConfigMenu(configResults);
						
						return results;
					}
					
				});
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

}
