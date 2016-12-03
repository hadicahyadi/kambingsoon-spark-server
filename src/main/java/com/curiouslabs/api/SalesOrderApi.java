package com.curiouslabs.api;

import static spark.Spark.*;

import java.util.Collections;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.SalesOrderDao;
import com.curiouslabs.model.SalesOrder;
import com.curiouslabs.model.SalesOrderDetail;
import com.google.gson.Gson;
import com.pusher.rest.Pusher;

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
				int result = 0;
				try{
					result = salesOrderDao.update(salesOrder);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				return "{\"result\":\"SUCCESS\"}";
			}});
		
		get(BASE_ROUTE+"/getByTable", new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				String table = request.queryParams("tableNumber");
				return gson.toJson(salesOrderDao.getByTable(table));
			}
			
		});
		
		post(BASE_ROUTE+"/requestBill", new Route(){

			@Override
			public Object handle(Request request, Response arg1) throws Exception {
				String body = request.body();
				Map map = gson.fromJson(body, Map.class);
				Pusher pusher = new Pusher("267259", "370734f1f09b6e884fcd", "7ca63e4860b0905290d6");
				pusher.setEncrypted(true);
				String message = "Table #"+map.get("table")+" is Requesting Bill!";
				pusher.trigger("bill_request_channel", "bill_request", Collections.singletonMap("message",message));
				return "{\"result\":\"SUCCESS\"}";
			}
			
		});
		
		get(BASE_ROUTE+"/tableActive", new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				String table = request.queryParams("IsActive");
				return gson.toJson(salesOrderDao.tableIsActive(table));
			}
			
		});
	}
}
