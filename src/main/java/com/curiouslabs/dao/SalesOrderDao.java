package com.curiouslabs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.curiouslabs.dao.mapper.SalesOrderDetailMapper;
import com.curiouslabs.dao.mapper.SalesOrderMapper;
import com.curiouslabs.model.SalesOrder;
import com.curiouslabs.model.SalesOrderDetail;
import com.curiouslabs.util.Datasource;

public class SalesOrderDao implements GenericDao<SalesOrder> {
	
	private QueryRunner run;
	
	public SalesOrderDao()
	{
		run = new QueryRunner(Datasource.getConnection());
	}

	@Override
	public Long save(SalesOrder salesOrder) {
		String sql = "insert into sales_order (transaction_date, payment_method, total_gross, discount, total_nett, table_no) "
				+ "values (?,?,?,?,?,?)";
		Long update = 0l;
		try{
			update = run.insert(sql, new ScalarHandler<Long>(), new Date(), salesOrder.getPaymentMethod(), salesOrder.getTotalGross(),
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
	
	public SalesOrder getByTable(String table){
		String sql = "select * from sales_order where table_no = "+table;
		SalesOrder salesOrder = new SalesOrder();
		try {
			salesOrder = run.query(sql, new ResultSetHandler<SalesOrder>(){

				@Override
				public SalesOrder handle(ResultSet rs) throws SQLException {
					int index = 0;
					SalesOrder salesOrder = new SalesOrderMapper().mapRow(rs, index);
					return salesOrder;
				}
			});
			
			String detailSql = "select s.*,m.* from sales_order_detail s"
					+ " join menu m on s.menu_id = m.id"
					+ " where sales_order_id = "+salesOrder.getId();
			
			@SuppressWarnings("unchecked")
			List<SalesOrderDetail> listDetail = (List<SalesOrderDetail>) run.query(detailSql, new ResultSetHandler<SalesOrderDetail>(){

				@Override
				public SalesOrderDetail handle(ResultSet rs) throws SQLException {
					int index = 0;
					SalesOrderDetail salesOrderDetail = new SalesOrderDetailMapper().mapRow(rs, index);
					return salesOrderDetail;
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salesOrder;
	}
}
