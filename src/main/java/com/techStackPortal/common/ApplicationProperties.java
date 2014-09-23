package com.techStackPortal.common;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationProperties {
	private static ResourceBundle rb;
	
	static{
		rb = ResourceBundle.getBundle("applicationProperties");
	}
	
	public static String getProperty(String key) {
		try {
			return rb.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			return "";
		}

	}

	public static String getProperty(String key, String defaultValue) {
		String val = null;
		try {
			val = rb.getString(key);
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}
		if (val == null) {
			val = defaultValue;
		}
		return val;
	}

}
