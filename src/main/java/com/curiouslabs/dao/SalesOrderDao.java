package com.curiouslabs.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.curiouslabs.model.SalesOrder;
import com.curiouslabs.util.Datasource;

public class SalesOrderDao implements GenericDao<SalesOrder> {
	
	private QueryRunner run;
	
	public SalesOrderDao()
	{
		run = new QueryRunner(Datasource.getConnection());
	}

	@Override
	public int save(SalesOrder salesOrder) {
		String sql = "insert into sales_order (transaction_date, payment_method, total_gross, discount, total_nett, table_no) "
				+ "values (?,?,?,?,?,?)";
		int update = 0;
		try{
			update = run.update(sql, new java.sql.Date(new Date().getTime()), salesOrder.getPaymentMethod(), salesOrder.getTotalGross(),
					salesOrder.getDiscount(), salesOrder.getTotalNett(), salesOrder.getTableNo());
		}catch(Exception e){
			e.printStackTrace();
		}
		return update;
		
	}

	@Override
	public List<SalesOrder> getAll() {
		
		String sql = "select * from salesorder";
		List<SalesOrder> list = null;
		List results = null;
		try{
			results = (List) run.query(sql, new MapListHandler());
			list = results;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return results;
		 
	}
}
