/**
 * 
 */
package com.curiouslabs.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import spark.resource.ClassPathResource;

/**
 * @author hadi
 *
 * Aug 15, 2016
 */
public class Datasource {
	
	public static BasicDataSource getConnection() {
		 BasicDataSource ds = null;
        try {
        	Properties props = new Properties();
            FileInputStream fis = null;
            fis = new FileInputStream(new ClassPathResource("jdbc.properties").getFile().getAbsolutePath());
            props.load(fis);
            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.username");
            String pw = props.getProperty("jdbc.password");
            
            ds = new BasicDataSource();
    		ds.setDriverClassName(props.getProperty("jdbc.driverClassName"));
    		ds.setUrl(url);
    		ds.setUsername(user);
    		ds.setPassword(pw);
 
           
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem komunikasi dengan server");
        }
        return ds;
    }

}
