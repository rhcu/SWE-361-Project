package kz.edu.nu.cs.exercise;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class User {
	private ResultSet rs;
	public String getUsername() throws SQLException {
		return rs.getString("userName");
	}
	public String getName() throws SQLException {
		return rs.getString("name");
	}
	public String getLastname() throws SQLException {
		return rs.getString("lastname");
	}

	public String getAddr() throws SQLException {
		return rs.getString("addr");
	}

	public int getAge() throws SQLException {
		return rs.getInt("age");
	}
	
	public User(String username) {
		try {
	         // The newInstance() call is a work around for some
	         // broken Java implementations
	         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	     } catch (Exception ex) {
	         // handle the errorr
	    	 System.out.println(ex.getMessage());
	     }
		 
		Connection conn = null;
		
	   	try {
			conn = DriverManager.getConnection(Config.getUrlMySQL());
			String query = "select * from student where username='"+username+"'";

			Statement ps = conn.createStatement(); // generates sql query
			
			
			ResultSet resultSet = ps.executeQuery(query);
			resultSet.next();
			this.rs = resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String auth(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = null;
		username = (String) session.getAttribute("username");
		return username;
	}
}
