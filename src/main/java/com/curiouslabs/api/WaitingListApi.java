package com.curiouslabs.api;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.WaitingListDao;
import com.curiouslabs.model.WaitingList;
import com.google.gson.Gson;

public class WaitingListApi extends GenericApi {
	
	private WaitingListDao waitingListDao;
	
	public WaitingListApi(String BASE_ROUTE)
	{
		waitingListDao = new WaitingListDao();
		
		post(BASE_ROUTE+"/save", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Gson gson = new Gson();
				WaitingList waitingList = gson.fromJson(request.body(), WaitingList.class);
				
				Long result = waitingListDao.save(waitingList);
				
				return "{\"result\":"+result+"}";
			}
		});
		
		get(BASE_ROUTE+"/getAll", new Route()
		{
			@Override
			public Object handle(Request request, Response response) throws Exception{
				return gson.toJson(waitingListDao.getAll());
			}
		});
		
		post(BASE_ROUTE+"/delete",new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				String id = request.queryParams("id");
				int row  = waitingListDao.remove(Long.parseLong(id));
				return "{\"result\":"+row+"}";
			}
		});
	}

}
