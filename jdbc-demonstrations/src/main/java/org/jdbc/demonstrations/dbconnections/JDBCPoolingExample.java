package org.jdbc.demonstrations.dbconnections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.common.entities.utils.PropertiesUtils;
import org.jdbc.demonstrations.dboperations.DBOperationsJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCPoolingExample extends DBOperationsJDBC {
	private static Logger logger = LoggerFactory.getLogger(JDBCExample.class);

	private static HikariConfig config = new HikariConfig();
	// private static HikariDataSource ds;
	// HikariDataSource is the implementation class of DataSource
	private static DataSource ds;

	static {
		Properties prop = PropertiesUtils.getApplicationProperties();

		config.setJdbcUrl(prop.getProperty("url"));
		config.setUsername(prop.getProperty("username"));
		config.setPassword(System.getProperty("DB_PASS"));
		config.setDriverClassName(prop.getProperty("driver"));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	@Override
	public Connection checkConnection() {
		Connection conn = null;
		try {
			long startTime = System.currentTimeMillis();
			conn = ds.getConnection();
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			logger.info("creating a new jdbc pooling connection in {} ms", duration);
		} catch (SQLException e) {
			throw new RuntimeException("database connection failed", e);
		}
		return conn;
	}

}