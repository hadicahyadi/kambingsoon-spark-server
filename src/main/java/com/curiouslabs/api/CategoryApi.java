/**
 * 
 */
package com.curiouslabs.api;

import java.util.Arrays;

import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.CategoryDao;
import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * @author hadi
 *
 * Aug 15, 2016
 */
public class CategoryApi{
	
	private CategoryDao categoryDao;
	private String BASE_ROUTE = "category";
	private Gson gson = new Gson();
	
	public CategoryApi(){
		categoryDao = new CategoryDao();
		
		get(BASE_ROUTE+"/getAll",new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				
				return gson.toJson(categoryDao.getall());
			}
		});
	}

}
