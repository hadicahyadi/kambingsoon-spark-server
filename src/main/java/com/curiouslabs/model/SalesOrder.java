package com.curiouslabs.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SalesOrder extends BaseModel{

		private Date transactionDate;
		private String paymentMethod;
		private BigDecimal totalGross;
		private int discount;
		private BigDecimal totalNett;
		private String tableNo;
		private String status;
		private Boolean isActive;
		private List<SalesOrderDetail> orders;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public List<SalesOrderDetail> getOrders() {
			return orders;
		}
		public void setOrders(List<SalesOrderDetail> orders) {
			this.orders = orders;
		}
		public Date getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(Date transactionDate) {
			this.transactionDate = transactionDate;
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
		public BigDecimal getTotalGross() {
			return totalGross;
		}
		public void setTotalGross(BigDecimal totalGross) {
			this.totalGross = totalGross;
		}
		public int getDiscount() {
			return discount;
		}
		public void setDiscount(int discount) {
			this.discount = discount;
		}
		public BigDecimal getTotalNett() {
			return totalNett;
		}
		public void setTotalNett(BigDecimal totalNett) {
			this.totalNett = totalNett;
		}
		public String getTableNo() {
			return tableNo;
		}
		public void setTableNo(String tableNo) {
			this.tableNo = tableNo;
		}
		public Boolean getIsActive() {
			return isActive;
		}
		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}
		
		
}
