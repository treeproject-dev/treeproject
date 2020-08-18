package Connections;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Hello world!
 *
 */
public class AppConnect 
{
	
	protected Connection conn;
	public AppConnect(){
	try {
	      Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver class
	      conn = DriverManager.getConnection(
	          "jdbc:mysql://www.999.id.lv/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", "root",
	          "Student007"); // Create connection
	      conn.setAutoCommit(false);
	    } catch (Exception e) {
	      System.err.println(e);
	    }
}
}