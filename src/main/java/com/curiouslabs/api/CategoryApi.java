/**
 * 
 */
package com.curiouslabs.api;

import java.util.Arrays;

import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.CategoryDao;
import com.curiouslabs.model.Category;
import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * @author hadi
 *
 *         Aug 15, 2016
 */
public class CategoryApi extends GenericApi{

//	public String BASE_ROUTE = "category";
	public Gson gson = new Gson();
	private CategoryDao categoryDao;

	/**
	 * @param BASE_ROUTE
	 */
	public CategoryApi(String BASE_ROUTE) {
		
		categoryDao = new CategoryDao();

		get(BASE_ROUTE + "/getAll", new Route() {

			@Override
			public Object handle(Request request, Response response)
					throws Exception {

				return gson.toJson(categoryDao.getAll());
			}
		});
	}

}
