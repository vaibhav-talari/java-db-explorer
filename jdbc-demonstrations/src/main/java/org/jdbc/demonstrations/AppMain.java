package org.jdbc.demonstrations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.common.entities.dtamodel.CustomersDTO;
import org.common.entities.dtamodel.OrderItemsDTO;
import org.common.entities.dtamodel.OrdersDTO;
import org.common.entities.dtamodel.ProductsDTO;
import org.jdbc.demonstrations.dbconnections.JDBCExample;
import org.jdbc.demonstrations.dbconnections.JDBCPoolingExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppMain {

	private static Logger logger = LoggerFactory.getLogger(AppMain.class);

	public static void main(String[] args) {
		CustomersDTO c = new CustomersDTO(null, "Jarvis", "jarvis@email.com", null, null);
		ProductsDTO p = new ProductsDTO(null, "phone", "digital device", new BigDecimal("1000"), 100, "electronics");
		OrdersDTO o = new OrdersDTO(null, c, new Timestamp(new Date().getTime()), "NEW", new BigDecimal("1000"));
		OrderItemsDTO oi = new OrderItemsDTO(null, p, o, 1, new BigDecimal(1000));

		logger.info("starting execution with jdbc");
		jdbcPlain(c, p, o, oi);
		logger.info("****************************");
		logger.info("starting execution with jdbc hikari pool");
		jdbcPoolWithHikari(c, p, o, oi);
		logger.info("****************************");

	}

	private static void jdbcPlain(CustomersDTO c, ProductsDTO p, OrdersDTO o, OrderItemsDTO oi) {
		// normal jdbc connection
		JDBCExample jdbcSimple = new JDBCExample();

		logger.info("starting data insert");

		Long customerId = jdbcSimple.insertCustomer(c);
		c.setId(customerId);// set customer id returned form database
		logger.info("customer inserted {}", customerId);

		Long productId = jdbcSimple.insertProduct(p);
		p.setId(productId);// set product id returned form database
		logger.info("product inserted {}", productId);

		o.setCustomers(c);// set customer object
		Long orderId = jdbcSimple.insertOrder(o);
		o.setId(orderId);// set product id returned form database
		logger.info("order inserted {}", orderId);

		oi.setOrders(o); // set order object
		oi.setProducts(p); // set product object
		Long orderItemId = jdbcSimple.insertOrderItem(oi);
		oi.setId(orderItemId);// set order-item id from database
		logger.info("order-item inserted {}", orderItemId);
		logger.info("------------------");

		logger.info("getting all users data");
		List<CustomersDTO> users = jdbcSimple.getAllCustomers();
		users.forEach(a -> {
			logger.info("database customers: {}", a);
		});
		List<ProductsDTO> products = jdbcSimple.getAllProducts();
		products.forEach(a -> {
			logger.info("database products: {}", a);
		});
		List<OrdersDTO> orders = jdbcSimple.getAllOrders();
		orders.forEach(a -> {
			logger.info("database orders: {}", a);
		});
		orders.forEach(a -> {
			jdbcSimple.getOrderItemsByOrderId(a.getId()).forEach(b -> {
				logger.info("database orders items: {}", b);
			});
		});
		logger.info("------------------");

		logger.info("deleting user by email id");
		boolean ch = jdbcSimple.deleteCustomerById(c.getId());
		logger.info("data deleted {}", ch);
		ch = jdbcSimple.deleteProductById(p.getId());
		logger.info("data deleted {}", ch);
		ch = jdbcSimple.deleteOrderById(o.getId());
		logger.info("data deleted {}", ch);
		ch = jdbcSimple.deleteOrderItemById(oi.getId());
		logger.info("data deleted {}", ch);
		logger.info("------------------");
	}

	private static void jdbcPoolWithHikari(CustomersDTO c, ProductsDTO p, OrdersDTO o, OrderItemsDTO oi) {
		// jdbc connection with Hikari pool
		JDBCPoolingExample jdbcPool = new JDBCPoolingExample();

		logger.info("starting data insert");

		Long customerId = jdbcPool.insertCustomer(c);
		c.setId(customerId);// set customer id returned form database
		logger.info("customer inserted {}", customerId);

		Long productId = jdbcPool.insertProduct(p);
		p.setId(productId);// set product id returned form database
		logger.info("product inserted {}", productId);

		o.setCustomers(c);// set customer object
		Long orderId = jdbcPool.insertOrder(o);
		o.setId(orderId);// set product id returned form database
		logger.info("order inserted {}", orderId);

		oi.setOrders(o); // set order object
		oi.setProducts(p); // set product object
		Long orderItemId = jdbcPool.insertOrderItem(oi);
		oi.setId(orderItemId);// set order-item id from database
		logger.info("order-item inserted {}", orderItemId);
		logger.info("------------------");

		logger.info("getting all users data");
		List<CustomersDTO> users = jdbcPool.getAllCustomers();
		users.forEach(a -> {
			logger.info("database customers: {}", a);
		});
		List<ProductsDTO> products = jdbcPool.getAllProducts();
		products.forEach(a -> {
			logger.info("database products: {}", a);
		});
		List<OrdersDTO> orders = jdbcPool.getAllOrders();
		orders.forEach(a -> {
			logger.info("database orders: {}", a);
		});
		orders.forEach(a -> {
			jdbcPool.getOrderItemsByOrderId(a.getId()).forEach(b -> {
				logger.info("database orders items: {}", b);
			});
		});
		logger.info("------------------");

		logger.info("deleting user by email id");
		boolean ch = jdbcPool.deleteCustomerById(c.getId());
		logger.info("data deleted {}", ch);
		ch = jdbcPool.deleteProductById(p.getId());
		logger.info("data deleted {}", ch);
		ch = jdbcPool.deleteOrderById(o.getId());
		logger.info("data deleted {}", ch);
		ch = jdbcPool.deleteOrderItemById(oi.getId());
		logger.info("data deleted {}", ch);
		logger.info("------------------");
	}

}
