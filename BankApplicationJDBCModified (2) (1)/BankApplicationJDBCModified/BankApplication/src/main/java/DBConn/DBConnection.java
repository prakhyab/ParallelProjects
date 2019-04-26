package DBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	Connection connection;
	public Connection connect() {
	
		
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");

     connection = DriverManager.getConnection(
			"jdbc:oracle:thin:@localhost:1521:xe", "prakhya",
			"prakhya");
	 } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	return connection;
	

}
}
