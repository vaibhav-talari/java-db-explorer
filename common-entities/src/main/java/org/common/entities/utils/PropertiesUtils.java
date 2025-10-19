package org.common.entities.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties getApplicationProperties() {
		Properties prop = new Properties();

		// Load properties file
		try (InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
			if (in == null) {
				throw new RuntimeException("jdbc.properties not found in classpath!");
			}
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException("unable to load jdbc.properties");
		}
		return prop;
	}

}
