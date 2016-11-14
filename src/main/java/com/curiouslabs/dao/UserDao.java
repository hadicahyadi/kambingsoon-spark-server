package com.curiouslabs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.curiouslabs.model.User;
import com.curiouslabs.util.Datasource;

public class UserDao implements GenericDao<User>{
	
	private final Logger log = LoggerFactory.getLogger(UserDao.class);
	
	private QueryRunner run;
	
	public UserDao(){
		run = new QueryRunner(Datasource.getConnection());
	}

	@Override
	public Long save(User object) throws SQLException {
		String sql = "insert into user(username,password,role) values(?,?,?)";
		Long id = run.insert(sql, new ScalarHandler<Long>(),object.toArray()); 
		return id;
	}
	
	public int remove(Long id){
		String sql = "delete from user where id = "+id;
		int row = 0;
		try{
			row = run.update(sql);
			log.info(row+" row(s) deleted");
		}catch(Exception e){
			e.printStackTrace();
		}
		return row;
	} 

	@Override
	public int update(User user) throws SQLException {
		String sql = "update user set username = ? , password = ?, role = ? where id = ?";
		int update = run.update(sql,user.getUsername(),user.getPassword(),user.getRole(),user.getId());
		return update;
	}

	@Override
	public List<User> getAll() throws SQLException {
		String sql = "select * from user";
		List<User> result = null;
		List list = null;
		try{
			list = (List) run.query(sql, new MapListHandler());
			result = list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public User getByUsernamePassword(String username,String password){
		String sql = "select * from user where username = '"+username+"' and password ='"+password+"'";
		User user = null;
		try {
			user = (User) run.query(sql, new MapHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
