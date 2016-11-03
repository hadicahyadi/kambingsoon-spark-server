package com.curiouslabs.dao.beanprocessor;

import com.curiouslabs.dao.mapper.CategoryMapper;
import com.curiouslabs.dao.mapper.GenericRowMapper;
import com.curiouslabs.model.Category;

public class CategoryBeanProcessor extends GenericBeanProcessor<Category>{

	public CategoryBeanProcessor() {
		super(new CategoryMapper());
	}

}
