package com.curiouslabs.dao;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.curiouslabs.model.WaitingList;
import com.curiouslabs.util.Datasource;

public class WaitingListDao implements GenericDao<WaitingList> {
	
	private QueryRunner run;
	
	public WaitingListDao()
	{
		run = new QueryRunner(Datasource.getConnection());
	}

	@Override
	public int save(WaitingList waitingList) {
		String sql = "insert into waiting_list (guest_name, guest_count)"+"values(?, ?)";
		int update = 0;
		
		try{
			update = run.update(sql, waitingList.getGuestName(), waitingList.getGuestCount());
		}catch(Exception e){
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public List<WaitingList> getAll() {
		String sql = "select * from waiting_list";
		List<WaitingList> list = null;
		List results = null;
		
		try{
			results = (List) run.query(sql, new MapListHandler());
			list = results;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return results;
	}
	
	

}
