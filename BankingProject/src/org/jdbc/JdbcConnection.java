package org.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
	
	private static Connection con;

	private JdbcConnection()
	{
		
	}
	
	public static Connection getDbConnection()
	{
		if(con==null)
		{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
		} 	
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
		}
		else
		{
			return con;
		}
	}
	
	public static void closeConnection()
	{
		try 
		{
			Jdbc.con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
