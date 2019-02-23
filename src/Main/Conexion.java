package Main;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	public Conexion() {
		// TODO Auto-generated constructor stub
	}
	
	public Connection getConexion() {
			
			Connection con = null;
			
			try {
				 // create a mysql database connection
			      String myDriver = "com.mysql.cj.jdbc.Driver";
			      String myUrl = "jdbc:mysql://localhost/csv?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			      Class.forName(myDriver);
			      con = DriverManager.getConnection(myUrl, "root", "");
				
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				System.out.print("Error en la conexion");
			}
			
			return con;
		}

}
