package kz.edu.nu.cs.exercise;
import java.sql.DriverManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * Servlet implementation class UserDataServlet
 */
public class RegistrationController extends HttpServlet {

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	 try {
         // The newInstance() call is a work around for some
         // broken Java implementations

         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
     } catch (Exception ex) {
         // handle the errorr
    	 System.out.println("AAAAA");
    	 System.out.println(ex.getMessage());
     }
	 
	 Connection conn = null;
	 String firstname = request.getParameter("firstname");

	 String lastname = request.getParameter("lastname");
	 String userName = request.getParameter("userName");
	 String pass = request.getParameter("pass");
	 String addr = request.getParameter("address");
	 String age = request.getParameter("age");
	
	 if (firstname.isEmpty() || lastname.isEmpty() || addr.isEmpty() || age.isEmpty() ) {
	   RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
	   request.setAttribute("error", "Please fill all the fields");
	   rd.include(request, response);
	  } else {
	   
		 try {
			conn = DriverManager.getConnection(Config.getUrlMySQL());
			String query = "insert into student(name, lastname, username, pass, addr, age) values(?,?,?,?,?,?)";
	
			PreparedStatement ps = conn.prepareStatement(query); // generates sql query
			
			pass = Config.md5(pass);
			
			ps.setString(1, firstname );
			ps.setString(2, lastname );
			ps.setString(3, userName);
			ps.setString(4, pass);
			ps.setString(5, addr);
			ps.setInt(6, Integer.parseInt(age));
	
	
			ps.executeUpdate(); // execute it on test database
			System.out.println("successfuly inserted");
			ps.close();
			conn.close();
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
			    
		 }catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			    RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
				//System.out.println("<font color=red>Please fill all the fields</font>");
			    request.setAttribute("error", ex.getMessage());
				rd.include(request, response);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			//System.out.println("<font color=red>Please fill all the fields</font>");
		    request.setAttribute("error", e.getMessage());
			rd.include(request, response);
		}
	// inserting data into mysql database 
	   // create a test database and student table before running this to create table
	   //create table student(name varchar(100), userName varchar(100), pass varchar(100), addr varchar(100), age int, qual varchar(100), percent varchar(100), year varchar(100));
		
	}


 }
}

