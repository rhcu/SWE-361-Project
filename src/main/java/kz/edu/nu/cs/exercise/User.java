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
	private String userName = "";
	private String name = "";
	private String lastname = "";
	private int age = 0;
	private String addr = "";
	public String getUsername() throws SQLException {
		return userName;
	}
	public String getName() throws SQLException {
		return name;
	}
	public String getLastname() throws SQLException {
		return lastname;
	}

	public String getAddr() throws SQLException {
		return addr;
	}

	public int getAge() throws SQLException {
		return age;
	}
	
	public User(String username) {
		try {
	         // The newInstance() call is a work around for some
	         // broken Java implementations
	         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	     } catch (Exception ex) {
	         // handle the error
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
			
			this.userName = resultSet.getString("userName");
			this.lastname = resultSet.getString("lastname");
			this.name = resultSet.getString("name");
			this.addr = resultSet.getString("addr");
			this.age = resultSet.getInt("age");
			resultSet.close();
			
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
