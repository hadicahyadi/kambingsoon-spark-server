/**
 * 
 */
package com.curiouslabs.model;

import java.math.BigDecimal;
import java.util.List;


public class Menu extends BaseModel{
	
	private Long parentId;
	private String menuName;
	private String imageName;
	private BigDecimal price;
	private String description;
	private Long categoryId;
	
	private Category category;
	private List<ConfigMenu> configMenu;
	
	
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
	public List<ConfigMenu> getConfigMenu() {
		return configMenu;
	}
	public void setConfigMenu(List<ConfigMenu> configMenu) {
		this.configMenu = configMenu;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
	
	

}
