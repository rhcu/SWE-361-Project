package kz.edu.nu.cs.exercise;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserDataServlet
 */
public class LoginController extends HttpServlet {

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	 try {
         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
     } catch (Exception ex) {
         // handle the error
    	 System.out.println(ex.getMessage());
     }
	 LogModel log = null;
	 try {
		 log = new LogModel();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 Connection conn = null;
	 PrintWriter out = response.getWriter();
	 String userName = request.getParameter("username");
	 String pass = request.getParameter("password");
	
	 if (userName.isEmpty() || pass.isEmpty() ) {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	   	request.setAttribute("error", "Please fill all the fields");
	   	out.println("<font color=red>Please fill all the fields</font>");
		rd.include(request, response);
	  } else {
	   
		 try {
			conn = DriverManager.getConnection(Config.getUrlMySQL());

			System.out.println("Database..");
	        Statement stmt = conn.createStatement();
	        Statement statement = conn.createStatement();
	        String db_username = null;
	        boolean found = false;
	        String db_password = null;
	            String query = "SELECT userName, pass FROM student;";
	            stmt.executeQuery(query);
	            ResultSet rs = stmt.getResultSet();

	            while(rs.next()){
	            	
	                db_username = rs.getString("userName");
	                db_password = rs.getString("pass");
	                String newPass = Config.encrypt(pass);
	                boolean matches = db_password.equals(newPass);
	                if((userName.equals("admin")) && matches) {
	                    found = true;
	                    log.add("login", request.getParameter("username") + " successfuly entered");	                 
	                    HttpSession session = request.getSession();
	                    session.setAttribute("username", request.getParameter("username"));
	                    RequestDispatcher rd = request.getRequestDispatcher("admin_panel.jsp");
	                    rd.forward(request, response);
	                }else {

	                	if(db_username.equals(userName) && !matches) {
	                		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	                		request.setAttribute("error", "Password is incorrect!");
	                		out.println("<font color=red>Password is incorrect!</font>");
	                		rd.include(request, response);
	                	}else if(db_username.equals(userName) && matches){
	                		found = true;
	                		log.add("login", request.getParameter("username") + " successfuly entered");
	                		User student = new User(request.getParameter("username"));
	                		HttpSession session = request.getSession();
	                		session.setAttribute("username", request.getParameter("username"));
	                		RequestDispatcher rd = request.getRequestDispatcher("home.jsp?firstname=" + student.getName() + "&lastname=" + student.getLastname() + "&address=" + student.getAddr() + "&age=" + student.getAge());
	                		rd.forward(request, response);
	                	}
	                }
	            }
	            if(!found) {
	       			log.add("login", request.getParameter("username") + " failed to enter");
	            }
	            rs.close();
       			conn.close();
				log.disconnect();

		 }catch (Exception ex) {
			    // handle any errors
			 	ex.printStackTrace();
			    
			    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				request.setAttribute("error", ex.getMessage());
			    out.println("<font color=red>" + ex.getMessage() + "</font>");
				rd.include(request, response);
		} 
	}
 }
}
