/**
 * 
 */
package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.Menu;

/**
 * @author hadi
 *
 * Aug 16, 2016
 */
public class MenuMapper {
	
	public Menu mapRow(ResultSet rs, int index) throws SQLException{
		Menu menu  = new Menu();
		menu.setId(rs.getInt(++index));
		menu.setParentId(rs.getInt(++index));
		menu.setMenuName(rs.getString(++index));
		menu.setImageName(rs.getString(++index));
		menu.setPrice(rs.getBigDecimal(++index));
		menu.setDescription(rs.getString(++index));
		menu.setCategoryId(rs.getInt(++index));
		return menu;
	}

}
