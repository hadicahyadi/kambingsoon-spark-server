/**
 * 
 */
package com.curiouslabs;

import static spark.Spark.*;

import java.util.Collections;
import java.util.HashMap;

import com.curiouslabs.api.CategoryApi;
import com.curiouslabs.api.MenuApi;
import com.curiouslabs.api.SalesOrderApi;
import com.curiouslabs.api.UserApi;
import com.curiouslabs.api.WaitingListApi;
import com.pusher.rest.Pusher;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;


public class Main {
	
	// Enables CORS on requests. This method is an initialization method and should be called once.
	private static void enableCORS(final String origin, final String methods, final String headers) {

	    options("/*", (request, response) -> {

	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }

	        return "OK";
	    });
	    

	    before((request, response) -> {
	        response.header("Access-Control-Allow-Origin", origin);
	        response.header("Access-Control-Request-Method", methods);
	        response.header("Access-Control-Allow-Headers", headers);
	        // Note: this may or may not be necessary in your particular application
//	        response.type("application/json");
	    });
	}
	
	private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();

	static {
		corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		corsHeaders.put("Access-Control-Allow-Origin", "*");
		corsHeaders.put("Access-Control-Allow-Headers",
				"Content-Disposition,Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
		corsHeaders.put("Access-Control-Allow-Credentials", "true");
	}

	public final static void apply() {
		Filter filter = new Filter() {
			@Override
			public void handle(Request request, Response response) throws Exception {
				corsHeaders.forEach((key, value) -> {
					response.header(key, value);
				});
			}
		};
//		Spark.before(filter);
		
		options("/*", (request, response) -> {

	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }

	        return "OK";
	    });
		Spark.after(filter);
	}

	public static void main(String args[]){
		port(8080); // Spark will run on port 8080
		enableCORS("*", "GET,PUT,POST,OPTIONS", "Content-Type, Accept,Origin, X-Requested-With, remember-me,Content-Disposition");
//		apply();
		
		// --- Add all api constructor here
		new CategoryApi("category");
		new MenuApi("menu");
		new SalesOrderApi("salesOrder");
		new WaitingListApi("waitingList");
		new UserApi("user");
		
		get("/", (request,response) -> "Hello World");
	}
	
}
