package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.WaitingList;

public class WaitingListMapper {

	public WaitingList mapRow(ResultSet rs, int index) throws SQLException
	{
		WaitingList waitinglist = new WaitingList();
		waitinglist.setId(rs.getInt(++index));
		waitinglist.setGuestName(rs.getString(++index));
		waitinglist.setGuestCount(rs.getString(++index));
		
		return waitinglist;
	}
}
