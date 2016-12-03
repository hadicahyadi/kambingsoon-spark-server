package com.curiouslabs.api;

import com.curiouslabs.dao.UserDao;
import com.curiouslabs.model.User;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserApi extends GenericApi{
	
	private UserDao userDao;
	
	public UserApi(String BASE_ROUTE){
		userDao = new UserDao();
		
		post(BASE_ROUTE+"/save", new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				gson = new Gson();
				User user = gson.fromJson(request.body(), User.class);
				Long result = userDao.save(user);
				return "{\"result\":"+result+"}";
			}
		});
		
		get(BASE_ROUTE+"/getAll",new Route(){

			@Override
			public Object handle(Request requset, Response response) throws Exception {
				List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
				try{
					result = userDao.getAllUser();
				}catch(Exception e){
					e.printStackTrace();
				}
				return gson.toJson(result);
			}
		});
		
		post(BASE_ROUTE+"/delete",new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				Long id = Long.parseLong(request.queryParams("id"));
				int row = userDao.remove(id);
				return "{\"result\":"+row+"}";
			}
			
		});
		
		get(BASE_ROUTE+"/login",new Route(){

			@Override
			public Object handle(Request request, Response arg1) throws Exception {
				Map<String,Object> mapResult = new HashMap<String,Object>();
				try{
					String username = request.queryParams("username");
					String password = request.queryParams("password");
					mapResult = userDao.getByUsernamePassword(username, password);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				if(mapResult == null){
					return "{\"result\":\"FAILED\"}";
				}
				return "{\"result\":"+gson.toJson(mapResult)+"}";
			}
			
		});
	}

}
