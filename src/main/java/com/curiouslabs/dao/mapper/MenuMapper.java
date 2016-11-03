/**
 * 
 */
package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.Category;
import com.curiouslabs.model.Menu;

public class MenuMapper implements GenericRowMapper<Menu>{
	
	public Menu mapRow(ResultSet rs, int index) throws SQLException{
		Menu menu  = new Menu();
		menu.setId(rs.getLong(++index));
		menu.setParentId(rs.getLong(++index));
		menu.setMenuName(rs.getString(++index));
		menu.setImageUrl(rs.getString(++index));
		menu.setPrice(rs.getBigDecimal(++index));
		menu.setDescription(rs.getString(++index));
		menu.setCategoryId(rs.getLong(++index));
		
		Category category = new Category();
		category.setId(rs.getLong(++index));
		category.setCategoryName(rs.getString(++index));
		menu.setCategory(category);
		index = 0;
		return menu;
	}

}
