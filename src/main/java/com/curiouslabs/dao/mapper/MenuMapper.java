/**
 * 
 */
package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.Menu;

public class MenuMapper {
	
	public Menu mapRow(ResultSet rs, int index) throws SQLException{
		Menu menu  = new Menu();
		menu.setId(rs.getLong(++index));
		menu.setParentId(rs.getLong(++index));
		menu.setMenuName(rs.getString(++index));
		menu.setImageName(rs.getString(++index));
		menu.setPrice(rs.getBigDecimal(++index));
		menu.setDescription(rs.getString(++index));
		menu.setCategoryId(rs.getLong(++index));
		index = 0;
		return menu;
	}

}
