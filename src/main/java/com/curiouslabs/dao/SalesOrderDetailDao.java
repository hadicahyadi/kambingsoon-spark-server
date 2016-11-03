package com.curiouslabs.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.curiouslabs.model.SalesOrderDetail;
import com.curiouslabs.util.Datasource;

public class SalesOrderDetailDao implements GenericDao<SalesOrderDetail>{
	
	private QueryRunner run;
	
	public SalesOrderDetailDao()
	{
		run = new QueryRunner(Datasource.getConnection());
	}
	
	
	@Override
	public Long save(SalesOrderDetail salesOrderDetail) {
		String sql = "insert into sales_order_detail (sales_order_id, menu_id, qty, subtotal)"
				+"values(?,?,?,?)";
		Long update = 0l;
		try{
			update = run.insert(sql,new ScalarHandler<Long>(),salesOrderDetail.getSalesOrderId(), salesOrderDetail.getMenuId(),
					salesOrderDetail.getQty(), salesOrderDetail.getSubtotal());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public List<SalesOrderDetail> getAll() {
		String sql = "select * from sales_order_detail";
		List<SalesOrderDetail> list = null;
		List results = null;
		try{
			results = (List) run.query(sql, new MapListHandler());
			list = results;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int update(SalesOrderDetail obhect) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
