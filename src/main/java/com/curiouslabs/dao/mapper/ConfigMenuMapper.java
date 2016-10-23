/**
 * 
 */
package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.ConfigMenu;

/**
 * @author hadi
 *
 * Aug 16, 2016
 */
public class ConfigMenuMapper implements GenericMapper<ConfigMenu>{

	/* (non-javadoc)
	 * 
	 * @see com.curiouslabs.dao.mapper.GenericMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ConfigMenu mapRow(ResultSet rs, int index) throws SQLException {
		ConfigMenu cm = new ConfigMenu();
		cm.setId(rs.getLong(++index));
		cm.setMenuId(rs.getInt(++index));
		cm.setQty(rs.getInt(++index));
		cm.setUnit(rs.getString(++index));
		cm.setPrice(rs.getInt(++index));
		
		return cm;
	}

}
