/**
 * 
 */
package com.curiouslabs.util;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.dbutils.BeanProcessor;

/**
 * @author hadi
 *
 * Aug 15, 2016
 */
public class StateBeanProcessor extends BeanProcessor{
	
	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,PropertyDescriptor[] props) throws SQLException {
		int[] mapping = super.mapColumnsToProperties(rsmd, props);
		/*
		 * Map database columns to fields in the order in which they appear 1st
		 * column in the DB will be mapped to 1st field in the Java class and so
		 * on..
		 */
		for (int i = 0; i < mapping.length; ++i) {
			mapping[i] = i;
		}
		
		return mapping;
	}

}
