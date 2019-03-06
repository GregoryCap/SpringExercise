package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.entity.User;
import com.example.demo.jdbc.DAOFactory;


public class UserDAO {
	
	DAOFactory daoFactory;
	
	public UserDAO(DAOFactory factory){
		this.daoFactory = factory;
	}
	
	public List<User> getAll(){
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM public.users");
			
			while (resultSet.next()) {
				users.add(new User(
						resultSet.getString("login"),
						resultSet.getString("lastname"),
						resultSet.getString("firstname")));              
            }
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dispose(connection,statement,resultSet);
		}
		return users;

	}
	
	public User getOne(String login) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		User user= null;
		
		try {
			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(String.format("SELECT * "
					+ "FROM public.users "
					+ "WHERE public.users.login='%s'", login));
			
			while (resultSet.next()) {
				user = new User(resultSet.getString("login"),
						resultSet.getString("lastname"),
						resultSet.getString("firstname"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			dispose(connection,statement,resultSet);
		}
		return user;
        
	}
	
	private void dispose(Connection conn, Statement statement, ResultSet rs) {
	  
		close(conn);
		close(statement);
		close(rs);
	}
	  
	  private void close(Connection conn) {
		  if ( conn != null ) {
		        try {
		        	conn.close();
		        } catch ( SQLException e ) {
		            System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
		        }
		  }
	  }
	  
	  private void close(Statement statement) {
		  if ( statement != null ) {
		        try {
		        	statement.close();
		        } catch ( SQLException e ) {
		            System.out.println( "Échec de la fermeture du statement : " + e.getMessage() );
		        }
		  }
	  }
	  
	  private void close(ResultSet rs) {
		  if ( rs != null ) {
		        try {
		        	rs.close();
		        } catch ( SQLException e ) {
		            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
		        }
		  }
	  }
	  
		  
}
