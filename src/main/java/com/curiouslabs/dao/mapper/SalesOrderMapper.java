package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.SalesOrder;

public class SalesOrderMapper {
	
	public SalesOrder mapRow(ResultSet rs, int index) throws SQLException
	{
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(rs.getInt(++index));
		salesOrder.setTransactionDate(rs.getDate(++index));
		salesOrder.setPaymentMethod(rs.getString(++index));
		salesOrder.setTotalGross(rs.getBigDecimal(++index));
		salesOrder.setDiscount(rs.getInt(++index));
		salesOrder.setTotalNett(rs.getBigDecimal(++index));
		salesOrder.setTableNo(rs.getInt(++index));
		return salesOrder;
	}
}