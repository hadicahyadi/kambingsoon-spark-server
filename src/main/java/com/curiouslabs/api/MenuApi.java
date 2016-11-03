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
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

/**
 * @author hadi
 *
 *         Aug 16, 2016
 */
public class MenuApi extends GenericApi {

	private MenuDao menuDao;

	/*
	 * @param BASE_ROUTE
	 */
	public MenuApi(String BASE_ROUTE) {
		menuDao = new MenuDao();

		get(BASE_ROUTE + "/getParentMenu", new Route() {

			@Override
			public Object handle(Request request, Response response) throws Exception {
				return gson.toJson(menuDao.getParentMenu());
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
				} catch (Exception e) {
					e.printStackTrace();
				}

				CloudinaryHelper cloudinaryHelper = new CloudinaryHelper();
				String imageUrl = cloudinaryHelper.uploadImage(buffer.toByteArray());
				Menu menu = gson.fromJson(request.body(), Menu.class);
				return "00";
			}

		});
	}

}
