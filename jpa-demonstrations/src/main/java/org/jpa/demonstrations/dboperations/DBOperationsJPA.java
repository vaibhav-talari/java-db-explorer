package org.jpa.demonstrations.dboperations;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.common.entities.dbentities.Customers;
import org.common.entities.dbentities.OrderItems;
import org.common.entities.dbentities.Orders;
import org.common.entities.dbentities.Products;
import org.common.entities.dtamodel.CustomersDTO;
import org.common.entities.dtamodel.OrderItemsDTO;
import org.common.entities.dtamodel.OrdersDTO;
import org.common.entities.dtamodel.ProductsDTO;
import org.common.entities.mapper.DTAToEntityMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public abstract class DBOperationsJPA {

	protected EntityManagerFactory entityManagerFactory;

	public abstract void createEntityFactory();

	public void close() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public Long insertCustomer(CustomersDTO customerDto) {
		Customers customers = DTAToEntityMapper.INSTANCE.customersToEntityMapping(customerDto);
		Function<EntityManager, Customers> unitWork = em -> {
			// using persist() the Users object reference should not be reused.
			// If the same Users object is passed twice to persist() then
			// EntityExistsException is thrown
			return em.merge(customers);
		};
		return executeTransaction(unitWork).getId();
	}

	public List<CustomersDTO> getAllCustomers() {
		List<CustomersDTO> customerDto = new ArrayList<>();

		Function<EntityManager, List<Customers>> unitWork = em -> {
			return em.createQuery("select c from Customers c", Customers.class).getResultList();
		};
		executeTransaction(unitWork).forEach(dbu -> {
			CustomersDTO cDto = DTAToEntityMapper.INSTANCE.customersFromEntityMapping(dbu);

			customerDto.add(cDto);
		});
		return customerDto;
	}

	public boolean deleteCustomerById(Long id) {
		Function<EntityManager, Integer> unitWork = em -> {
			return em.createQuery("DELETE FROM Customers c WHERE c.id = :id").setParameter("id", id).executeUpdate();
		};

		int deletedCount = executeTransaction(unitWork);
		return deletedCount > 0;
	}

	public Long insertProduct(ProductsDTO productDto) {
		Products products = DTAToEntityMapper.INSTANCE.productsToEntityMapping(productDto);
		Function<EntityManager, Products> unitWork = em -> {
			return em.merge(products);
		};
		return executeTransaction(unitWork).getId();
	}

	public List<ProductsDTO> getAllProducts() {
		List<ProductsDTO> productsDto = new ArrayList<>();

		Function<EntityManager, List<Products>> unitWork = em -> {
			return em.createQuery("select p from Products p", Products.class).getResultList();
		};
		executeTransaction(unitWork).forEach(dbu -> {
			ProductsDTO productDto = DTAToEntityMapper.INSTANCE.productsFromEntityMapping(dbu);

			productsDto.add(productDto);
		});
		return productsDto;
	}

	public boolean deleteProductById(Long id) {
		Function<EntityManager, Integer> unitWork = em -> {
			return em.createQuery("DELETE FROM Products p WHERE p.id = :id").setParameter("id", id).executeUpdate();
		};

		int deletedCount = executeTransaction(unitWork);
		return deletedCount > 0;
	}

	public Long insertOrder(OrdersDTO ordersDto) {
		Orders orders = DTAToEntityMapper.INSTANCE.ordersToEntityMapping(ordersDto);
		Function<EntityManager, Orders> unitWork = em -> {
			return em.merge(orders);
		};
		return executeTransaction(unitWork).getId();
	}

	public List<OrdersDTO> getAllOrders() {
		List<OrdersDTO> ordersDto = new ArrayList<>();

		Function<EntityManager, List<Orders>> unitWork = em -> {
			List<Orders> orders = em.createQuery("select o from Orders o", Orders.class).getResultList();
			// doing a simple read to access the customer object within
			// the JPA session so that its loaded and available to
			// the mapper object after the session is closed
			// alternatively try #not tested#:
			// "SELECT o FROM Orders o JOIN FETCH o.customers"
			orders.forEach(o -> o.getCustomers().getName());
			return orders;
		};
		executeTransaction(unitWork).forEach(dbu -> {
			OrdersDTO orderDto = DTAToEntityMapper.INSTANCE.ordersFromEntityMapping(dbu);

			ordersDto.add(orderDto);
		});
		return ordersDto;
	}

	public boolean deleteOrderById(Long id) {
		Function<EntityManager, Integer> unitWork = em -> {
			return em.createQuery("DELETE FROM Orders o WHERE o.id = :id").setParameter("id", id).executeUpdate();
		};

		int deletedCount = executeTransaction(unitWork);
		return deletedCount > 0;
	}

	public Long insertOrderItem(OrderItemsDTO orderItemsDto) {
		OrderItems ordersItems = DTAToEntityMapper.INSTANCE.orderItemsToEntityMapping(orderItemsDto);
		Function<EntityManager, OrderItems> unitWork = em -> {
			return em.merge(ordersItems);
		};
		return executeTransaction(unitWork).getId();
	}

	public List<OrderItemsDTO> getOrderItemsByOrderId(Long id) {
		List<OrderItemsDTO> ordersItemsDto = new ArrayList<>();

		Function<EntityManager, List<OrderItems>> unitWork = em -> {
			return em.createQuery("select oi from OrderItems oi WHERE oi.id = :id", OrderItems.class)
					.setParameter("id", id).getResultList();
		};
		executeTransaction(unitWork).forEach(dbu -> {
			OrderItemsDTO orderItemsDto = DTAToEntityMapper.INSTANCE.orderItemsFromEntityMapping(dbu);

			ordersItemsDto.add(orderItemsDto);
		});
		return ordersItemsDto;
	}

	public boolean deleteOrderItemById(Long id) {
		Function<EntityManager, Integer> unitWork = em -> {
			return em.createQuery("DELETE FROM OrderItems oi WHERE oi.id = :id").setParameter("id", id).executeUpdate();
		};

		int deletedCount = executeTransaction(unitWork);
		return deletedCount > 0;
	}

	private <T> T executeTransaction(Function<EntityManager, T> work) {
		// create entity manager
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			T result = work.apply(entityManager);
			transaction.commit();
			return result;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
