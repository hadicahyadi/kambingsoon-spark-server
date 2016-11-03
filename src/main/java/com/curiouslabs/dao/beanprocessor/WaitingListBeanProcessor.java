package com.curiouslabs.dao.beanprocessor;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;
import org.eclipse.jetty.server.RequestLog;

import com.curiouslabs.dao.mapper.WaitingListMapper;
import com.curiouslabs.model.WaitingList;

public class WaitingListBeanProcessor extends GenericBeanProcessor<WaitingList>{

	public WaitingListBeanProcessor() {
		super(new WaitingListMapper());
	}
	
	

}
