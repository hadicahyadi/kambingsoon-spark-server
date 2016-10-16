package com.curiouslabs.api;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.SalesOrderDao;
import com.curiouslabs.model.SalesOrder;
import com.google.gson.Gson;

public class SalesOrderApi extends GenericApi {
	
	private SalesOrderDao salesOrderDao;
	
	public SalesOrderApi(String BASE_ROUTE){
		salesOrderDao = new SalesOrderDao();
		
		post(BASE_ROUTE+"/save", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Gson gson = new Gson();
				SalesOrder salesOrder = gson.fromJson(request.body(), SalesOrder.class);
				
				int result = salesOrderDao.save(salesOrder);
				
				return "{\"result\":"+result+"}";
			}
		});
	}
}
