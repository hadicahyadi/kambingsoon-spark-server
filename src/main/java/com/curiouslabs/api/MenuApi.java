/**
 * 
 */
package com.curiouslabs.api;

import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.MenuDao;
import com.google.gson.Gson;

import static spark.Spark.*;
/**
 * @author hadi
 *
 * Aug 16, 2016
 */
public class MenuApi {
	
	private MenuDao menuDao;
	private String BASE_ROUTE = "menu";
	private Gson gson = new Gson();
	
	public MenuApi(){
		menuDao = new MenuDao();
		
		get(BASE_ROUTE+"/getParentMenu", new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				return gson.toJson(menuDao.getParentMenu());
			}
			
		});
		
		get(BASE_ROUTE+"/getChildMenu", new Route(){
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String parentId = request.queryParams("parentId");
				return gson.toJson(menuDao.getChildMenu(Integer.valueOf(parentId)));
			}
			
		});
	}

}
