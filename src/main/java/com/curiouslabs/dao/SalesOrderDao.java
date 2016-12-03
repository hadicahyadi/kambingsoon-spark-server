package com.curiouslabs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.curiouslabs.dao.mapper.MenuMapper;
import com.curiouslabs.dao.mapper.SalesOrderDetailMapper;
import com.curiouslabs.dao.mapper.SalesOrderMapper;
import com.curiouslabs.model.Menu;
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
		String sql = "select * from sales_order where table_no = '"+table+"' and status = 'UNPAID'";
		SalesOrder salesOrder = new SalesOrder();
		try {
			salesOrder = run.query(sql, new ResultSetHandler<SalesOrder>(){

				@Override
				public SalesOrder handle(ResultSet rs) throws SQLException {
					int index = 0;
					SalesOrder salesOrder = new SalesOrder();
					while(rs.next()){
						salesOrder = new SalesOrderMapper().mapRow(rs, index);
					}
					return salesOrder;
				}
			});
			
			String detailSql = "select s.*,m.*,c.* from sales_order_detail s"
					+ " join menu m on s.menu_id = m.id"
					+ " join category c on m.category_id = c.id"
					+ " where sales_order_id = "+salesOrder.getId();
			
			@SuppressWarnings("unchecked")
			List<SalesOrderDetail> listDetail = run.query(detailSql, new ResultSetHandler<List<SalesOrderDetail>>(){
				List<SalesOrderDetail> listDetail = new ArrayList<SalesOrderDetail>();
				@Override
				public List<SalesOrderDetail> handle(ResultSet rs) throws SQLException {
					int index = 0;
					List<SalesOrderDetail> listDetail = new ArrayList<SalesOrderDetail>();
					while(rs.next()){
						SalesOrderDetail salesOrderDetail = new SalesOrderDetailMapper().mapRow(rs, index);
						index += 4;
						Menu menu = new MenuMapper().mapRow(rs, index);
						salesOrderDetail.setMenu(menu);
						listDetail.add(salesOrderDetail);
						index = 0;
					}
					return listDetail;
				}
			});
			salesOrder.setOrders(listDetail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salesOrder;
	}
	
	public int saveDetail(SalesOrderDetail salesOrderDetail){
		String sql = "insert into sales_order_detail (sales_order_id, menu_id, qty, subtotal)"
				+" values(?,?,?,?)";
		int update = 0;
		try{
			update = run.update(sql, salesOrderDetail.getSalesOrderId(), salesOrderDetail.getMenuId(),
					salesOrderDetail.getQty(), salesOrderDetail.getSubtotal());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return update;
		
	}

	@Override
	public int update(SalesOrder salesOrder) throws SQLException {
		int result = 0;
		try{
			String sql = "update sales_order set discount = ?, total_nett = ?, payment_method = ?,"
					+ "status = ?,  table_no = ? where id = ?";
			result = run.update(sql,salesOrder.getDiscount(),salesOrder.getTotalNett(),
					salesOrder.getPaymentMethod(),salesOrder.getStatus(),salesOrder.getTableNo(),salesOrder.getId());
			System.out.println(result+" row(s) updated");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
