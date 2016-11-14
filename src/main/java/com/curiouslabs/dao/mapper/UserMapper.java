package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.User;

public class UserMapper implements GenericRowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int index) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(++index));
		user.setUsername(rs.getString(++index));
		user.setPassword(rs.getString(++index));
		user.setRole(rs.getString(++index));
		index = 0;
		return user;
	}

}
