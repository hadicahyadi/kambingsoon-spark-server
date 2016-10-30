package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.SalesOrderDetail;

public class SalesOrderDetailMapper {

	public SalesOrderDetail mapRow(ResultSet rs, int index) throws SQLException
	{
		SalesOrderDetail salesOrderDetail = new SalesOrderDetail();
		salesOrderDetail.setSalesOrderId(rs.getLong(++index));
		salesOrderDetail.setMenuId(rs.getInt(++index));
		salesOrderDetail.setQty(rs.getInt(++index));
		salesOrderDetail.setSubTotal(rs.getBigDecimal(++index));
		index = 0;
		return salesOrderDetail;
		
	}
}
