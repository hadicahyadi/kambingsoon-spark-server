package com.curiouslabs.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiouslabs.model.Category;

public class CategoryMapper implements GenericRowMapper<Category>{

	@Override
	public Category mapRow(ResultSet rs, int index) throws SQLException {
		Category category = new Category();
		category.setId(rs.getLong(++index));
		category.setCategoryName(rs.getString(++index));
		return category;
	}

}
