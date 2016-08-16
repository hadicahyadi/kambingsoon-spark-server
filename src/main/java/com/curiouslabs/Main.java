/**
 * 
 */
package com.curiouslabs;

import static spark.Spark.*;

import com.curiouslabs.api.CategoryApi;
import com.curiouslabs.api.MenuApi;


/**
 * @author hadi
 *
 * Aug 14, 2016
 */
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
	        response.type("application/json");
	    });
	}

	public static void main(String args[]){
		port(8080); // Spark will run on port 8080
		enableCORS("Origin", "GET,PUT,POST", "Content-Type, Accept, X-Requested-With, remember-me");
		
		// --- Add all api constructor here
		new CategoryApi("category");
		new MenuApi("menu");
		
		get("/", (request,response) -> "Hello World");
	}
	
}
