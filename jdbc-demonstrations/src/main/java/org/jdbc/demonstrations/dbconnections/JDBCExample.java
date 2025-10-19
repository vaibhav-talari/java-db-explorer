package org.jdbc.demonstrations.dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.common.entities.utils.PropertiesUtils;
import org.jdbc.demonstrations.dboperations.DBOperationsJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCExample extends DBOperationsJDBC {
	private static Logger logger = LoggerFactory.getLogger(JDBCExample.class);

	static final String DB_URL;
	static final String USER;
	static final String PASS;
	static final String DRIVER;

	static {
		Properties prop = PropertiesUtils.getApplicationProperties();

		// Read properties
		USER = prop.getProperty("username");
		DB_URL = prop.getProperty("url");
		DRIVER = prop.getProperty("driver");

		// Override password from VM argument, if provided
		PASS = System.getProperty("DB_PASS");
		if (PASS == null || PASS.isEmpty()) {
			throw new RuntimeException("password not found!");
		}
	}

	@Override
	public Connection checkConnection() {
		Connection conn = null;
		try {
			long startTime = System.currentTimeMillis();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			logger.info("creating a new jdbc connection in {} ms", duration);
		} catch (SQLException e) {
			throw new RuntimeException("database connection failed", e);
		}
		return conn;
	}

}
