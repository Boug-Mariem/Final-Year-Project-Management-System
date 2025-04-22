package interfacePFE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	private static String serveurBD ="jdbc:oracle:thin:@localhost:1521:XE";
	private static String login = "GestionPFE";
	private static String motPasse = "oracle";
	private static Connection conn;
	
	
	private MyConnection()  {
			
			//conn = DriverManager.getConnection(serveurBD, login, motPasse);
	}
	
	public static Connection getConnection()throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		if (conn==null)
			conn = DriverManager.getConnection(serveurBD, login, motPasse);
		return conn;
	}
}
