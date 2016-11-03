/**
 * 
 */
package com.curiouslabs.model;

import java.math.BigDecimal;
import java.util.List;


public class Menu extends BaseModel implements BaseModelInterface{
	
	private Long parentId;
	private String menuName;
	private String imageUrl;
	private BigDecimal price;
	private String description;
	private Long categoryId;
	
	private Category category;
	
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public Object[] toArray() {
		return new Object[]{parentId,menuName,imageUrl,price,description,categoryId};
	}
	
	

}
