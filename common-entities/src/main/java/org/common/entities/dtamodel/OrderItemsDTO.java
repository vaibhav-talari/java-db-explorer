package org.common.entities.dtamodel;

import java.math.BigDecimal;

public class OrderItemsDTO {

	private Long id;
	private ProductsDTO products;
	private OrdersDTO orders;
	private int quantity;
	private BigDecimal unitPrice;

	public OrderItemsDTO(Long id, ProductsDTO products, OrdersDTO orders, int quantity, BigDecimal unitPrice) {
		super();
		this.id = id;
		this.products = products;
		this.orders = orders;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductsDTO getProducts() {
		return products;
	}

	public void setProducts(ProductsDTO products) {
		this.products = products;
	}

	public OrdersDTO getOrders() {
		return orders;
	}

	public void setOrders(OrdersDTO orders) {
		this.orders = orders;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "OrderItemsDTO [id=" + id + ", products=" + products + ", orders=" + orders + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + "]";
	}

}
