package com.example.demo.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.example.demo.dao.UserDAO;

public class DAOFactory {
	 private static final String PROPERTY_FILE       = "dao.properties";
	    private static final String PROPERTY_URL             = "url";
	    private static final String PROPERTY_USERNAME = "username";
	    private static final String PROPERTY_PASSWORD    = "password";
	    
	    private String url;
	    private String username;
	    private String password;

	    public DAOFactory(String url, String username, String password) {
			this.url = url;
			this.username = username;
			this.password = password;
		}
	    
	    public static DAOFactory getInstance() throws DAOConfigurationException {
	    	Properties properties = new Properties();
	    	String url;
	    	String username;
	    	String password;
	    	
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream propertyFile = classLoader.getResourceAsStream( PROPERTY_FILE );
			
			if ( propertyFile == null ) {
			    throw new DAOConfigurationException( "Le fichier properties " + PROPERTY_FILE + " est introuvable." );
			}
			
			try {
				properties.load(propertyFile);
				url = properties.getProperty(PROPERTY_URL);
				username = properties.getProperty(PROPERTY_USERNAME);
				password = properties.getProperty(PROPERTY_PASSWORD);
			} 
			catch (IOException e) {
				throw new DAOException("Impossible de charger le fichier properties " + PROPERTY_FILE, e );
	        }
			
			DAOFactory instance = new DAOFactory(url,username, password);
			return instance;
		}	    
	    
	    Connection getConnection() throws SQLException{
	    	return DriverManager.getConnection(url, username, password);
	    }
	    
	    public UserDAO getUserDAO(){
	    	return new UserDAO (this);
	    }
}
