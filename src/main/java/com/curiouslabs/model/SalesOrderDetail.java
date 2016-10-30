package com.curiouslabs.model;

import java.math.BigDecimal;

public class SalesOrderDetail extends BaseModel{
	
	private Long salesOrderId;
	private int menuId;
	private int qty;
	private BigDecimal subtotal;
	
	
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubTotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
	
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
	public Long getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
}
