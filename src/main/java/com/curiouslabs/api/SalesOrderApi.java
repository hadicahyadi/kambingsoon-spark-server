package com.curiouslabs.api;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.SalesOrderDao;
import com.curiouslabs.model.SalesOrder;
import com.curiouslabs.model.SalesOrderDetail;
import com.google.gson.Gson;

public class SalesOrderApi extends GenericApi {
	
	private SalesOrderDao salesOrderDao;
	
	private Gson gson = new Gson();
	
	public SalesOrderApi(String BASE_ROUTE){
		
		salesOrderDao = new SalesOrderDao();
		
		post(BASE_ROUTE+"/save", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				System.out.println("try to posting orders ="+ request.body());
				Gson gson = new Gson();
				SalesOrder salesOrder = gson.fromJson(request.body(), SalesOrder.class);
				
				Long salesOrderId = salesOrderDao.save(salesOrder);
				for(SalesOrderDetail detail : salesOrder.getOrders()){
					detail.setSalesOrderId(salesOrderId);
					salesOrderDao.saveDetail(detail);
				}
				System.out.println(salesOrderId);
				return "{\"result\":"+salesOrderId+"}";
			}
		});
		
		post(BASE_ROUTE+"/update", new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				System.out.println("[REQUEST BODY] - "+request.body());
				SalesOrder salesOrder = gson.fromJson(request.body(), SalesOrder.class);
				int result = salesOrderDao.update(salesOrder);
				return "{\"result\":\"SUCCESS\"}";
			}});
		
		get(BASE_ROUTE+"/getByTable", new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				String table = request.queryParams("tableNumber");
				return gson.toJson(salesOrderDao.getByTable(table));
			}
			
		});
	}
}
