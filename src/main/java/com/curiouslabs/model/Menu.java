/**
 * 
 */
package com.curiouslabs.model;

import java.util.List;

/**
 * @author hadi
 *
 * Aug 16, 2016
 */
public class Menu extends BaseModel{
	
	private int parentId;
	private String menuName;
	private String imageName;
	private String description;
	private int categoryId;
	
	private Category category;
	private List<ConfigMenu> configMenu;
	
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
