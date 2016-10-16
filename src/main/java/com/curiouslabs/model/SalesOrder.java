package com.curiouslabs.model;

import java.math.BigDecimal;
import java.util.Date;

public class SalesOrder extends BaseModel{

		private Date transactionDate;
		private String paymentMethod;
		private BigDecimal totalGross;
		private int discount;
		private BigDecimal totalNett;
		private int tableNo;
		
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
		public int getTableNo() {
			return tableNo;
		}
		public void setTableNo(int tableNo) {
			this.tableNo = tableNo;
		}
		
		
		
		
}
