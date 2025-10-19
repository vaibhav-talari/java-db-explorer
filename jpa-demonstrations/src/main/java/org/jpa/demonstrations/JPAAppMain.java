package org.jpa.demonstrations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.common.entities.dtamodel.CustomersDTO;
import org.common.entities.dtamodel.OrderItemsDTO;
import org.common.entities.dtamodel.OrdersDTO;
import org.common.entities.dtamodel.ProductsDTO;
import org.jpa.demonstrations.dbconnections.JPAHibernateExample;
import org.jpa.demonstrations.dbconnections.JPAHibernateWithHikariExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAAppMain {

	private static Logger logger = LoggerFactory.getLogger(JPAAppMain.class);

	public static void main(String[] args) {

		logger.info("starting execution with jdbc");
		jpawithHibernate();
		logger.info("****************************");
		logger.info("starting execution with jdbc hikari pool");
		jpawithHibernateWithHikari();
		logger.info("****************************");
	}

	private static void jpawithHibernate() {
		CustomersDTO c = new CustomersDTO(null, "Jarvis", "jarvis@email.com", null, null);
		ProductsDTO p = new ProductsDTO(null, "phone", "digital device", new BigDecimal("1000"), 100, "electronics");
		OrdersDTO o = new OrdersDTO(null, c, new Timestamp(new Date().getTime()), "NEW", new BigDecimal("1000"));
		OrderItemsDTO oi = new OrderItemsDTO(null, p, o, 1, new BigDecimal(1000));

		// database access with jpa with Hibernate as the provider
		JPAHibernateExample jpa = new JPAHibernateExample();

		logger.info("starting data insert");

		Long customerId = jpa.insertCustomer(c);
		c.setId(customerId);// set customer id returned form database
		logger.info("customer inserted {}", customerId);

		Long productId = jpa.insertProduct(p);
		p.setId(productId);// set product id returned form database
		logger.info("product inserted {}", productId);

		o.setCustomers(c);// set customer object
		Long orderId = jpa.insertOrder(o);
		o.setId(orderId);// set product id returned form database
		logger.info("order inserted {}", orderId);

		oi.setOrders(o); // set order object
		oi.setProducts(p); // set product object
		Long orderItemId = jpa.insertOrderItem(oi);
		oi.setId(orderItemId);// set order-item id from database
		logger.info("order-item inserted {}", orderItemId);
		logger.info("------------------");

		logger.info("getting all users data");
		List<CustomersDTO> users = jpa.getAllCustomers();
		users.forEach(a -> {
			logger.info("database customers: {}", a);
		});
		List<ProductsDTO> products = jpa.getAllProducts();
		products.forEach(a -> {
			logger.info("database products: {}", a);
		});
		List<OrdersDTO> orders = jpa.getAllOrders();
		orders.forEach(a -> {
			logger.info("database orders: {}", a);
		});
		orders.forEach(a -> {
			jpa.getOrderItemsByOrderId(a.getId()).forEach(b -> {
				logger.info("database orders items: {}", b);
			});
		});
		logger.info("------------------");

		logger.info("deleting user by email id");
		boolean ch = jpa.deleteCustomerById(c.getId());
		logger.info("data deleted {}", ch);
		ch = jpa.deleteProductById(p.getId());
		logger.info("data deleted {}", ch);
		ch = jpa.deleteOrderById(o.getId());
		logger.info("data deleted {}", ch);
		ch = jpa.deleteOrderItemById(oi.getId());
		logger.info("data deleted {}", ch);
		logger.info("------------------");

		jpa.close();
	}

	private static void jpawithHibernateWithHikari() {
		// TODO: check duplicate object persistence and how to keep object
		// reference fresh
		// duplicated the object reference since the mapper returns the same object
		// which results in same entity within the jpa session
		CustomersDTO c = new CustomersDTO(null, "Jarvis", "jarvis@email.com", null, null);
		ProductsDTO p = new ProductsDTO(null, "phone", "digital device", new BigDecimal("1000"), 100, "electronics");
		OrdersDTO o = new OrdersDTO(null, c, new Timestamp(new Date().getTime()), "NEW", new BigDecimal("1000"));
		OrderItemsDTO oi = new OrderItemsDTO(null, p, o, 1, new BigDecimal(1000));

		// database access with jpa with Hibernate as the provider
		JPAHibernateWithHikariExample jpa = new JPAHibernateWithHikariExample();

		logger.info("starting data insert");

		Long customerId = jpa.insertCustomer(c);
		c.setId(customerId);// set customer id returned form database
		logger.info("customer inserted {}", customerId);

		Long productId = jpa.insertProduct(p);
		p.setId(productId);// set product id returned form database
		logger.info("product inserted {}", productId);

		o.setCustomers(c);// set customer object
		Long orderId = jpa.insertOrder(o);
		o.setId(orderId);// set product id returned form database
		logger.info("order inserted {}", orderId);

		oi.setOrders(o); // set order object
		oi.setProducts(p); // set product object
		Long orderItemId = jpa.insertOrderItem(oi);
		oi.setId(orderItemId);// set order-item id from database
		logger.info("order-item inserted {}", orderItemId);
		logger.info("------------------");

		logger.info("getting all users data");
		List<CustomersDTO> users = jpa.getAllCustomers();
		users.forEach(a -> {
			logger.info("database customers: {}", a);
		});
		List<ProductsDTO> products = jpa.getAllProducts();
		products.forEach(a -> {
			logger.info("database products: {}", a);
		});
		List<OrdersDTO> orders = jpa.getAllOrders();
		orders.forEach(a -> {
			logger.info("database orders: {}", a);
		});
		orders.forEach(a -> {
			jpa.getOrderItemsByOrderId(a.getId()).forEach(b -> {
				logger.info("database orders items: {}", b);
			});
		});
		logger.info("------------------");

		logger.info("deleting user by email id");
		boolean ch = jpa.deleteCustomerById(c.getId());
		logger.info("data deleted {}", ch);
		ch = jpa.deleteProductById(p.getId());
		logger.info("data deleted {}", ch);
		ch = jpa.deleteOrderById(o.getId());
		logger.info("data deleted {}", ch);
		ch = jpa.deleteOrderItemById(oi.getId());
		logger.info("data deleted {}", ch);
		logger.info("------------------");

		jpa.close();
	}
}
