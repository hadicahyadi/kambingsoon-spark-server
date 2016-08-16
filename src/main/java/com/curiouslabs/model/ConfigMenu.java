/**
 * 
 */
package com.curiouslabs.model;

/**
 * @author hadi
 *
 * Aug 16, 2016
 */
public class ConfigMenu extends BaseModel{
	
	private int menuId;
	private int qty;
	private String unit;
	private int price;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
