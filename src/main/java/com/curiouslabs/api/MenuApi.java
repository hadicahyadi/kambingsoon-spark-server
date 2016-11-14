/**
 * 
 */
package com.curiouslabs.api;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import com.cloudinary.Cloudinary;
import com.curiouslabs.dao.MenuDao;
import com.curiouslabs.helper.CloudinaryHelper;
import com.curiouslabs.model.Menu;
import com.google.gson.Gson;

import static spark.Spark.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hadi
 *
 *         Aug 16, 2016
 */
public class MenuApi extends GenericApi {
	private final Logger log = LoggerFactory.getLogger(MenuApi.class);

	private MenuDao menuDao;

	/*
	 * @param BASE_ROUTE
	 */
	public MenuApi(String BASE_ROUTE) {
		menuDao = new MenuDao();

		get(BASE_ROUTE + "/getParentMenu", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				Long categoryId = Long.parseLong(request.queryParams("categoryId"));
				log.info(request.url()+request.queryString());
				return gson.toJson(menuDao.getParentMenu(categoryId));
			}
		});
		
		get(BASE_ROUTE + "/getAllParentMenu", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				log.info(request.url()+request.queryString());
				return gson.toJson(menuDao.getAllParentMenu());
			}
		});
		
		get(BASE_ROUTE + "/getAll",new Route(){

			@Override
			public Object handle(Request arg0, Response arg1) throws Exception {
				return gson.toJson(menuDao.getAll());
			}
			
		});

		get(BASE_ROUTE + "/getChildMenu", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				String parentId = request.queryParams("parentId");
				List<Menu> result = new ArrayList<>();
				try {
					result = menuDao.getByParent(Long.parseLong(parentId));
				} catch (Exception e) {
					e.printStackTrace();
				}

				return gson.toJson(result);
			}
		});

		post(BASE_ROUTE + "/save", new Route() {
			

			@Override
			public Object handle(Request request, Response response) throws Exception {
				InputStream is = null;
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				try {
					String location = "/images"; // the directory location where
													// files will be stored
					long maxFileSize = 100000000; // the maximum size allowed
													// for uploaded files
					long maxRequestSize = 100000000; // the maximum size allowed
														// for
														// multipart/form-data
														// requests
					int fileSizeThreshold = 1024; // the size threshold after
													// which files will be
													// written to disk

					MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize,
							maxRequestSize, fileSizeThreshold);
					request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
					Part file = request.raw().getPart("imageFile"); // file is
																	// name of
																	// the
																	// upload
																	// form
					is = file.getInputStream();

					int nRead;
					byte[] data = new byte[16384];

					while ((nRead = is.read(data, 0, data.length)) != -1) {
						buffer.write(data, 0, nRead);
					}

					buffer.flush();
					
					CloudinaryHelper cloudinaryHelper = new CloudinaryHelper();
					Map<String,Object> mapResponse = cloudinaryHelper.uploadImage(buffer.toByteArray());
					String body = request.raw().getParameter("data");
					Menu menu = gson.fromJson(body, Menu.class);
					String imageUrl=(String) mapResponse.get("url");
					System.out.println("URL IMAGE: "+imageUrl);
					menu.setImageUrl(imageUrl);
					menu.setMenuName(menu.getMenuName().toUpperCase());
					menuDao.save(menu);
				} catch (Exception e) {
					e.printStackTrace();
				}

				
				return "{\"result\":\"SUCCESS\"}";
			}

		});
		
		post(BASE_ROUTE+"/updatePrice",new Route(){

			@Override
			public Object handle(Request request, Response response) throws Exception {
				BigDecimal price = new BigDecimal(request.queryParams("price"));
				Long id = Long.parseLong(request.queryParams("id"));
				Menu menu = new Menu();
				menu.setId(id);
				menu.setPrice(price);
				int row = menuDao.update(menu);
				return "{\"result\":"+row+"}";
				
			}
			
		});
	}

}
