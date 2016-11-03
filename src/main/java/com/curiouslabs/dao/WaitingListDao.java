package com.curiouslabs.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.curiouslabs.dao.beanprocessor.WaitingListBeanProcessor;
import com.curiouslabs.model.WaitingList;
import com.curiouslabs.util.Datasource;

public class WaitingListDao implements GenericDao<WaitingList> {
	
	private QueryRunner run;
	
//	public WaitingListDao() {
//		super("waiting_list");
//		insertSql = "insert into waiting_list (guest_name, guest_count)"+"values(?, ?)";
//		rs = new WaitingListMap();
//	}
	
	public WaitingListDao()
	{
		run = new QueryRunner(Datasource.getConnection());
	}

	@Override
	public Long save(WaitingList waitingList) {
		String sql = "insert into waiting_list (guest_name, guest_count)"+"values(?, ?)";
		Long update = 0l;
		
		try{
			update = run.insert(sql, new ScalarHandler<Long>(),waitingList.toArray());
		}catch(Exception e){
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public List<WaitingList> getAll() {
		String sql = "select * from waiting_list";
		List<WaitingList> result = null;
		try{
			result = (List<WaitingList>) run.query(sql, new BeanListHandler<WaitingList>(WaitingList.class,new WaitingListBeanProcessor()));

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String args[]) throws SQLException{
		System.out.println(new WaitingListDao().getAll().get(0).getGuestName());
	}

	@Override
	public int update(WaitingList obhect) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
