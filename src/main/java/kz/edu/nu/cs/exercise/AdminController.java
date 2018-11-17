package kz.edu.nu.cs.exercise;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet(urlPatterns = { "/admin_panel" })
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> fields = new ArrayList<String>();
	List<String> values = new ArrayList<String>();
	
  
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 String username = (String)session.getAttribute("username");
		 List<String> fields1 = new ArrayList<String>();
		 List<String> values1 = new ArrayList<String>();
		 if(username.equals("admin")) {
			 String res = "<!DOCTYPE html><html><head><title> Menu </title> </head> <body>"
	 				+ "<h1> Logs </h1><p><ul>"; 
			 try {
		         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		     } catch (Exception ex) {
		         // handle the errorr
		    	
		    	 System.out.println(ex.getMessage());
		     }
			 Connection conn = null;
			 try {
				 conn = DriverManager.getConnection(Config.getUrlMySQL());
	
				 System.out.println("Database..");
				 Statement stmt = conn.createStatement();
				        
				 String db_type = null;
				
				 String db_value = null;
		
			           
				 String query = "SELECT type, content FROM cvbuilder_logs;";
				 stmt.executeQuery(query);
				 ResultSet rs = stmt.getResultSet();
				            
				 while(rs.next()){
				            	
				 	db_type = rs.getString("type");
				 	db_value = rs.getString("content");
				 	fields1.add(db_type);
				 	values1.add(db_value);
		
				 }
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
	 		for(int i = 0; i < values1.size(); i++) {
	 			String type = fields1.get(i);
	 			String value = values1.get(i);
	 			
	 			res += "<li> <b>" + type + "</b> " + value +  "</li>";
	 		}
	 		res+= "</ul></p></body></html>";
	 	    response.setContentType("text/html");
	 	    response.getWriter().println(res);
		}
	 }
	
	 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
	 //doGet(request, response);
	 try {
         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
     } catch (Exception ex) {
         // handle the errorr
    	
    	 System.out.println(ex.getMessage());
     }
	
	 Connection conn = null;
	 PrintWriter out = response.getWriter();
	
		 try {
			conn = DriverManager.getConnection(Config.getUrlMySQL());

			System.out.println("Database..");
	        Statement stmt = conn.createStatement();
	        
	        String db_type = null;
	
	        String db_value = null;

           
	            String query = "SELECT type, content FROM cvbuilder_logs;";
	            stmt.executeQuery(query);
	            ResultSet rs = stmt.getResultSet();
	            
	            while(rs.next()){
	            	
	                db_type = rs.getString("type");
	                db_value = rs.getString("content");
	                fields.add(db_type + "\n");
		    		values.add(db_value + "\n");

	            }
	           
	            doGet(request, response);
	            rs.close();
       			conn.close();

		 }catch (Exception ex) {
			    // handle any errors
			 	ex.printStackTrace();
			    
			    RequestDispatcher rd = request.getRequestDispatcher("admin_panel.jsp");
				request.setAttribute("error", ex.getMessage());
			    out.println("<font color=red>" + ex.getMessage() + "</font>");
			   
				rd.include(request, response);
		} 
	
 }
 
}

