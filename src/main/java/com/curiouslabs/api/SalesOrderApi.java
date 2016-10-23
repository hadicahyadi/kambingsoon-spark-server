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
				System.out.println("try to posting orders ="+ request.body());
				Gson gson = new Gson();
				SalesOrder salesOrder = gson.fromJson(request.body(), SalesOrder.class);
				
				Long result = salesOrderDao.save(salesOrder);
				System.out.println(result);
				return "{\"result\":"+result+"}";
			}
		});
	}
}
