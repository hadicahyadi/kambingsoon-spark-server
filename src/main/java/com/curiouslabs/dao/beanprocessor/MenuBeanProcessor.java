package com.curiouslabs.dao.beanprocessor;

import com.curiouslabs.dao.mapper.GenericRowMapper;
import com.curiouslabs.dao.mapper.MenuMapper;
import com.curiouslabs.model.Menu;

public class MenuBeanProcessor extends GenericBeanProcessor<Menu>{

	public MenuBeanProcessor() {
		super(new MenuMapper());
		// TODO Auto-generated constructor stub
	}

}
