package org.common.entities.dtamodel;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrdersDTO {

	private Long id;
	private CustomersDTO customers;
	private Timestamp orderDate;
	private String status;
	private BigDecimal totalAmount;

	public OrdersDTO(Long id, CustomersDTO customers, Timestamp orderDate, String status, BigDecimal totalAmount) {
		super();
		this.id = id;
		this.customers = customers;
		this.orderDate = orderDate;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomersDTO getCustomers() {
		return customers;
	}

	public void setCustomers(CustomersDTO customers) {
		this.customers = customers;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "OrdersDTO [id=" + id + ", customers=" + customers + ", orderDate=" + orderDate + ", status=" + status
				+ ", totalAmount=" + totalAmount + "]";
	}

}
