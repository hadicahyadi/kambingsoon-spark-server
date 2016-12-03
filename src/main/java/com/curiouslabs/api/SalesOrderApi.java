package com.curiouslabs.api;

import static spark.Spark.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;

import com.curiouslabs.dao.SalesOrderDao;
import com.curiouslabs.model.SalesOrder;
import com.curiouslabs.model.SalesOrderDetail;
import com.curiouslabs.util.Datasource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pusher.rest.Pusher;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class SalesOrderApi extends GenericApi {

	private SalesOrderDao salesOrderDao;

	private Gson gson = new Gson();

	public SalesOrderApi(String BASE_ROUTE) {

		salesOrderDao = new SalesOrderDao();

		post(BASE_ROUTE + "/save", new Route() {

			@Override
			public Object handle(Request request, Response response)
					throws Exception {
				System.out.println("try to posting orders =" + request.body());
				Gson gson = new Gson();
				SalesOrder salesOrder = gson.fromJson(request.body(),
						SalesOrder.class);

				Long salesOrderId = salesOrderDao.save(salesOrder);
				for (SalesOrderDetail detail : salesOrder.getOrders()) {
					detail.setSalesOrderId(salesOrderId);
					salesOrderDao.saveDetail(detail);
				}
				System.out.println(salesOrderId);
				return "{\"result\":" + salesOrderId + "}";
			}
		});

		post(BASE_ROUTE + "/update", new Route() {

			@Override
			public Object handle(Request request, Response response)
					throws Exception {
				System.out.println("[REQUEST BODY] - " + request.body());
				SalesOrder salesOrder = null;
				int result = 0;
				Gson gsonSalesOrder = new GsonBuilder().setDateFormat(
						"MMM dd, yyyy").create();
				try {
					salesOrder = gsonSalesOrder.fromJson(request.body(),
							SalesOrder.class);
					result = salesOrderDao.update(salesOrder);
					Map<String, Object> mapParam = new HashMap<String, Object>();
					mapParam.put("table", salesOrder.getTableNo());

					ClassLoader classLoader = getClass().getClassLoader();
					File file = new File(classLoader.getResource("report/struct.jrxml").getFile());
					JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		            JasperPrint print = JasperFillManager.fillReport(report, mapParam, Datasource.getConnection().getConnection());
		            DateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		            String filename = "struct_"+df.format(new Date());
		            File pdf = File.createTempFile(filename, ".pdf");
//		            response.header("Content-Disposition", String.format("attachment; filename="+filename));
//		            response.type("application/pdf");
		            JasperExportManager.exportReportToPdfStream(print,response.raw().getOutputStream());
//		            response. ("{\"result\":"+filename+"}");
				}catch(Exception e){
					e.printStackTrace();
				}

				return "{\"result\":\"SUCCESS\"}";
			}
		});

		get(BASE_ROUTE + "/getByTable", new Route() {

			@Override
			public Object handle(Request request, Response response)
					throws Exception {
				String table = request.queryParams("tableNumber");
				return gson.toJson(salesOrderDao.getByTable(table));
			}

		});

		post(BASE_ROUTE + "/requestBill", new Route() {

			@Override
			public Object handle(Request request, Response arg1)
					throws Exception {
				String body = request.body();
				Map map = gson.fromJson(body, Map.class);
				Pusher pusher = new Pusher("267259", "370734f1f09b6e884fcd",
						"7ca63e4860b0905290d6");
				pusher.setEncrypted(true);
				String message = "Table #" + map.get("table")
						+ " is Requesting Bill!";
				pusher.trigger("bill_request_channel", "bill_request",
						Collections.singletonMap("message", message));
				return "{\"result\":\"SUCCESS\"}";
			}

		});

		get(BASE_ROUTE + "/tableActive", new Route() {

			@Override
			public Object handle(Request request, Response response)
					throws Exception {
				List<String> result = new ArrayList<>();
				try {
					result = salesOrderDao.getTableActive();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return gson.toJson(result);
			}
		});
	}
}
