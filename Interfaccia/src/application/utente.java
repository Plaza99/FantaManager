package application;
import java.nio.charset.StandardCharsets;
import java.sql.*;

import com.google.common.hash.Hashing;
public class utente {
	public static boolean insert_utent(Connection con,String nick,String pass) {
		try {
		
			String ins="INSERT into utenti SET nick=?, password=?";
			PreparedStatement ps=con.prepareStatement(ins);
			ps.setString(1, nick);
			String sha256hex = Hashing.sha256().hashString(pass, StandardCharsets.UTF_8).toString();
			ps.setString(2, sha256hex);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	public static boolean exists(Connection con,String nick) {
		try {
			Statement st=con.createStatement();
			st.execute("SELECT * from utenti where nick= \""+nick+"\"");
			ResultSet rs=st.getResultSet();
			return rs.next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	//0 password errata
	//1 utente normale
	//2 e oltre admin
	public static int logIn(Connection con,String nick,String pass) {
		try {
			Statement st=con.createStatement();
			String sha256hex = Hashing.sha256().hashString(pass, StandardCharsets.UTF_8).toString();
			st.execute("SELECT * from utenti where nick= \""+nick+"\" and password=\""+sha256hex+"\"");
			ResultSet rs=st.getResultSet();
			if(rs.next()) {
				global.nick=rs.getString("nick");
				global.idUtente=rs.getInt("userId");
				return isAdmin(con,rs.getInt("userId"));
			}
			else {
				return 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private static int isAdmin(Connection con,int id) throws SQLException {
		Statement st=con.createStatement();
		st.execute("SELECT * from admin where userId= \""+id+"\"");
		ResultSet rs=st.getResultSet();
		if(rs.next()) {
			return rs.getInt("privilegeLevel");
		}
		else {
			return 1;
		}
	}

}
