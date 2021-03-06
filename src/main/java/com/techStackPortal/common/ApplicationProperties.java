package com.techStackPortal.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * @author SumeetS
 *
 */
public class ApplicationProperties {
	private static ResourceBundle rb;
	private static final Logger LOGGER = Logger
			.getLogger(ApplicationProperties.class);

	static {
		rb = ResourceBundle.getBundle("applicationProperties");
	}

	public static String getProperty(String key) {
		try {
			return rb.getString(key);
		} catch (MissingResourceException e) {
			LOGGER.debug("Error initializing resource bundle", e);
			return "";
		}

	}

	public static String getProperty(String key, String defaultValue) {
		String val = null;
		try {
			val = rb.getString(key);
		} catch (MissingResourceException e) {
			LOGGER.debug("Error initializing resource bundle", e);
		}
		if (val == null) {
			val = defaultValue;
		}
		return val;
	}

}
