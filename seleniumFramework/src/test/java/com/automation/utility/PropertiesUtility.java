package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	
	private FileInputStream file = null;
	private Properties prop = null;
	
	public Properties loadFile(String fileName) {
			prop = new Properties();
		String propertyFilePath = null;
		switch(fileName) {
		case "applicationProperties":
			propertyFilePath = Constants.APPLICATION_PROPERTIES;
			break;
		case"studentDataProperties":
			propertyFilePath = Constants.STUDENT_REGISTRATION_PROPERTIES;
			break;
		case "applicationDataProperties":
			propertyFilePath = Constants.APPLICATION_DATA_PROPERTIES;
			break;
		}
		
		try {
			file = new FileInputStream(propertyFilePath);
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return prop;
	}
	
	public String getPropertyValue(String key) {		
		String value = prop.getProperty(key);
		return value;
	}
	
	public void writeDataPropertyFile(File path, String key, String value) {
		 prop = new Properties();
		prop.setProperty(key, value);
		try {
			prop.store(new FileOutputStream(path), "Updating data");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
