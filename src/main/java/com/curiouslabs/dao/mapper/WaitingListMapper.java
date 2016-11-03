package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import com.curiouslabs.model.WaitingList;

public class WaitingListMapper implements GenericRowMapper<WaitingList>{

	public WaitingList mapRow(ResultSet rs, int index) throws SQLException
	{
		WaitingList waitinglist = new WaitingList();
		waitinglist.setId(rs.getLong(++index));
		waitinglist.setGuestName(rs.getString(++index));
		waitinglist.setGuestCount(rs.getInt(++index));
		
		return waitinglist;
	}
}
