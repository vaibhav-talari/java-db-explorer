package org.jpa.demonstrations.dbconnections;

import org.jpa.demonstrations.dboperations.DBOperationsJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Persistence;

public class JPAHibernateWithHikariExample extends DBOperationsJPA {
	private static Logger logger = LoggerFactory.getLogger(JPAHibernateExample.class);

	public JPAHibernateWithHikariExample() {
		createEntityFactory();
	}

	@Override
	public void createEntityFactory() {
		// createEntityManagerFactory name matches with the name provided in
		// persistent.xml
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hikari");
		logger.info("entity manager factory created");
	}

}
