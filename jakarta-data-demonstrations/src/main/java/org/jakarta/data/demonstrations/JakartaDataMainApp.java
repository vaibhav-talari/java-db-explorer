package org.jakarta.data.demonstrations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.common.entities.dbentities.Customers;
import org.common.entities.dbentities.OrderItems;
import org.common.entities.dbentities.Orders;
import org.common.entities.dbentities.Payments;
import org.common.entities.dbentities.Products;
import org.common.entities.dtamodel.CustomersDTO;
import org.common.entities.dtamodel.OrderItemsDTO;
import org.common.entities.dtamodel.OrdersDTO;
import org.common.entities.dtamodel.ProductsDTO;
import org.common.entities.mapper.DTAToEntityMapper;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.jakarta.data.demonstrations.repository.CustomersRepository;
import org.jakarta.data.demonstrations.repository.CustomersRepository_;
import org.jakarta.data.demonstrations.repository.OrderItemsRepository;
import org.jakarta.data.demonstrations.repository.OrderItemsRepository_;
import org.jakarta.data.demonstrations.repository.OrdersRepository;
import org.jakarta.data.demonstrations.repository.OrdersRepository_;
import org.jakarta.data.demonstrations.repository.ProductsRepository;
import org.jakarta.data.demonstrations.repository.ProductsRepository_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JakartaDataMainApp {

	private static Logger logger = LoggerFactory.getLogger(JakartaDataMainApp.class);

	public static void main(String[] args) {
		var config = new HibernatePersistenceConfiguration("Jakarta Data Example").managedClasses(Customers.class,
				Orders.class, OrderItems.class, Payments.class, Products.class);

		try (var sessionFactory = config.createEntityManagerFactory()) {
			// export the schema and test data
			// var schemaManager = sessionFactory.getSchemaManager();
			// schemaManager.drop(true);
			// schemaManager.create(true);

			sessionFactory.inStatelessSession(session -> {
				// repository is usually injected via CDI,
				// but here we just instantiate it
				final CustomersRepository customersRepo = new CustomersRepository_(session);
				final ProductsRepository productRepo = new ProductsRepository_(session);
				final OrdersRepository orderRepo = new OrdersRepository_(session);
				final OrderItemsRepository orderItemRepo = new OrderItemsRepository_(session);

				// load a Book
				CustomersDTO c = new CustomersDTO(null, "Jarvis", "jarvis@email.com", null, null);
				ProductsDTO p = new ProductsDTO(null, "phone", "digital device", new BigDecimal("1000"), 100,
						"electronics");
				OrdersDTO o = new OrdersDTO(null, c, new Timestamp(new Date().getTime()), "NEW",
						new BigDecimal("1000"));
				OrderItemsDTO oi = new OrderItemsDTO(null, p, o, 1, new BigDecimal(1000));

				Customers cdb = DTAToEntityMapper.INSTANCE.customersToEntityMapping(c);
				Products pdb = DTAToEntityMapper.INSTANCE.productsToEntityMapping(p);
				Orders odb = DTAToEntityMapper.INSTANCE.ordersToEntityMapping(o);
				OrderItems oidb = DTAToEntityMapper.INSTANCE.orderItemsToEntityMapping(oi);

				logger.info("starting data insert");

				cdb = customersRepo.insert(cdb);
				logger.info("customer inserted {}", cdb.getId());

				pdb = productRepo.insert(pdb);
				logger.info("product inserted {}", pdb.getId());

				odb.setCustomers(cdb);// set customer object
				odb = orderRepo.insert(odb);
				logger.info("order inserted {}", odb.getId());

				oidb.setOrders(odb); // set order object
				oidb.setProducts(pdb); // set product object
				oidb = orderItemRepo.insert(oidb);
				logger.info("order-item inserted {}", oidb.getId());
				logger.info("------------------");

				logger.info("getting all users data");

				// run some more @Find-style queries
				customersRepo.findAll().forEach(cust -> {
					logger.info("customer email: {}", cust.getEmail());
				});

				productRepo.findAll().forEach(prd -> {
					logger.info("product name: {}", prd.getName());
				});

				orderRepo.findAll().forEach(ord -> {
					logger.info("order status: {}", ord.getStatus());
				});

				orderItemRepo.findAll().forEach(oir -> {
					logger.info("order unit price: {}", oir.getUnitPrice());
				});
				logger.info("------------------");

				logger.info("deleting data");
				customersRepo.delete(cdb);
				productRepo.delete(pdb);
				// order and order-item delete not required
				// since customers products cascade the deletion
				// orderRepo.delete(odb);
				// orderItemRepo.delete(oidb);
				logger.info("------------------");
			});
		}
	}

}
