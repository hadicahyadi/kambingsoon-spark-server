/**
 * 
 */
package com.curiouslabs.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author hadi
 *
 * Aug 15, 2016
 */
public class Category extends BaseModel{
	
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
