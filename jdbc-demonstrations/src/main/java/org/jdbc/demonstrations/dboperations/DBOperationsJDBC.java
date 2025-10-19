package org.jdbc.demonstrations.dboperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.common.entities.dtamodel.CustomersDTO;
import org.common.entities.dtamodel.OrderItemsDTO;
import org.common.entities.dtamodel.OrdersDTO;
import org.common.entities.dtamodel.ProductsDTO;

public abstract class DBOperationsJDBC {

	public abstract Connection checkConnection();

	public Long insertCustomer(CustomersDTO customer) {
		long generatedId = -1;
		String sql = "INSERT INTO customers(name,email) VALUES(?,?)";
		// prepareStatement(sql,new String[] { "id", "created_at" }) can also be used
		// but mariadb does not seem to support this
		try (Connection conn = checkConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getEmail());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("inserting customer failed, no rows affected.");
			}

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					// only one value is returned that is the auto increment column
					generatedId = rs.getLong(1);
				} else {
					throw new SQLException("inserting customer failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to insert customer: " + e.getMessage(), e);
		}
		return generatedId;
	}

	public List<CustomersDTO> getAllCustomers() {
		List<CustomersDTO> customers = new ArrayList<>();
		String sql = "SELECT id,name,email,created_at,active FROM customers";
		try (Connection conn = checkConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				CustomersDTO c = new CustomersDTO(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
						rs.getTimestamp("created_at"), rs.getBoolean("active"));
				customers.add(c);
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to fetch customers: " + e.getMessage(), e);
		}
		return customers;
	}

	public boolean deleteCustomerById(long customerId) {
		boolean deleted = false;
		String sql = "DELETE FROM customers WHERE id=?";
		try (Connection conn = checkConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, customerId);
			deleted = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			throw new RuntimeException("unable to delete customer: " + e.getMessage(), e);
		}
		return deleted;
	}

	public Long insertProduct(ProductsDTO product) {
		long generatedId = -1;
		String sql = "INSERT INTO products(name,description,price,stock_quantity,category) VALUES(?,?,?,?,?)";
		try (Connection conn = checkConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getDescription());
			pstmt.setBigDecimal(3, product.getPrice());
			pstmt.setInt(4, product.getStockQuantity());
			pstmt.setString(5, product.getCategory());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("inserting product failed, no rows affected.");
			}

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					generatedId = rs.getLong(1);
				} else {
					throw new SQLException("inserting product failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to insert product: " + e.getMessage(), e);
		}
		return generatedId;
	}

	public List<ProductsDTO> getAllProducts() {
		List<ProductsDTO> products = new ArrayList<>();
		String sql = "SELECT id,name,description,price,stock_quantity,category FROM products";
		try (Connection conn = checkConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				ProductsDTO p = new ProductsDTO(rs.getLong("id"), rs.getString("name"), rs.getString("description"),
						rs.getBigDecimal("price"), rs.getInt("stock_quantity"), rs.getString("category"));
				products.add(p);
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to fetch products: " + e.getMessage(), e);
		}
		return products;
	}

	public boolean deleteProductById(long productId) {
		boolean deleted = false;
		String sql = "DELETE FROM products WHERE id=?";
		try (Connection conn = checkConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, productId);
			deleted = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			throw new RuntimeException("unable to delete product: " + e.getMessage(), e);
		}
		return deleted;
	}

	public Long insertOrder(OrdersDTO order) {
		long generatedId = -1;
		String sql = "INSERT INTO orders(customer_id,order_date,status,total_amount) VALUES(?,?,?,?)";
		try (Connection conn = checkConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pstmt.setLong(1, order.getCustomers().getId());
			pstmt.setTimestamp(2, order.getOrderDate());
			pstmt.setString(3, order.getStatus());
			pstmt.setBigDecimal(4, order.getTotalAmount());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("inserting product failed, no rows affected.");
			}

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					generatedId = rs.getLong(1);
				} else {
					throw new SQLException("inserting product failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to insert order: " + e.getMessage(), e);
		}
		return generatedId;
	}

	public List<OrdersDTO> getAllOrders() {
		List<OrdersDTO> orders = new ArrayList<>();
		String sql = "SELECT id,customer_id,order_date,status,total_amount FROM orders";
		try (Connection conn = checkConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				OrdersDTO o = new OrdersDTO(rs.getLong("id"),
						new CustomersDTO(rs.getLong("customer_id"), null, null, null, null),
						rs.getTimestamp("order_date"), rs.getString("status"), rs.getBigDecimal("total_amount"));
				orders.add(o);
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to fetch orders: " + e.getMessage(), e);
		}
		return orders;
	}

	public boolean deleteOrderById(long orderId) {
		boolean deleted = false;
		String sql = "DELETE FROM orders WHERE id=?";
		try (Connection conn = checkConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, orderId);
			deleted = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			throw new RuntimeException("unable to delete order: " + e.getMessage(), e);
		}
		return deleted;
	}

	public Long insertOrderItem(OrderItemsDTO item) {
		long generatedId = -1;
		String sql = "INSERT INTO order_items(order_id,product_id,quantity,unit_price) VALUES(?,?,?,?)";
		try (Connection conn = checkConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pstmt.setLong(1, item.getOrders().getId());
			pstmt.setLong(2, item.getProducts().getId());
			pstmt.setInt(3, item.getQuantity());
			pstmt.setBigDecimal(4, item.getUnitPrice());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("inserting product failed, no rows affected.");
			}

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					generatedId = rs.getLong(1);
				} else {
					throw new SQLException("inserting product failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to insert order item: " + e.getMessage(), e);
		}
		return generatedId;
	}

	public List<OrderItemsDTO> getOrderItemsByOrderId(long orderId) {
		List<OrderItemsDTO> items = new ArrayList<>();
		String sql = "SELECT id,order_id,product_id,quantity,unit_price FROM order_items WHERE order_id=?";
		try (Connection conn = checkConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, orderId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					OrderItemsDTO i = new OrderItemsDTO(rs.getLong("id"),
							new ProductsDTO(rs.getLong("product_id"), null, null, null, null, null),
							new OrdersDTO(rs.getLong("order_id"), null, null, null, null), rs.getInt("quantity"),
							rs.getBigDecimal("unit_price"));
					items.add(i);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("unable to fetch order items: " + e.getMessage(), e);
		}
		return items;
	}

	public boolean deleteOrderItemById(long itemId) {
		boolean deleted = false;
		String sql = "DELETE FROM order_items WHERE id=?";
		try (Connection conn = checkConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setLong(1, itemId);
			deleted = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			throw new RuntimeException("unable to delete order item: " + e.getMessage(), e);
		}
		return deleted;
	}

}
