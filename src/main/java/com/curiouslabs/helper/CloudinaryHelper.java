package com.curiouslabs.helper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

public class CloudinaryHelper {
	
	private Cloudinary cloudinary;
	
	public CloudinaryHelper(){
		cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dlcxe58s7", "api_key", "982139957214656",
				"api_secret", "QnrMZMv4jh_6_HOihIU7IR_Xc-4"));
	}
	
	public String uploadImage(byte[] file){
		Gson gson = new Gson();
		Map responseMap = null;
		try {
			responseMap = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder","MENU_IMAGE"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) responseMap.get("url");
	}

}
