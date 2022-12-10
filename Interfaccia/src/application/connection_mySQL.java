package application;

import java.sql.*;

public class connection_mySQL {
	private Connection c;
	private static connection_mySQL instance = null;
	private connection_mySQL() {
		try {
			
			this.c=DriverManager.getConnection("jdbc:mysql://localhost:3306/Prova","root","1234");
		}catch(Exception  e) {
			
		}
	}
	public static Connection get_connection() {
		if (instance == null) {
            instance = new connection_mySQL();
        }
		return instance.c;
	}
}
