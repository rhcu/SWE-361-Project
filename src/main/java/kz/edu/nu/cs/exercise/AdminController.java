package kz.edu.nu.cs.exercise;
import java.sql.DriverManager;
import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;


/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet(urlPatterns = { "/admin_panel" })
public class AdminController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  List<String> fields = new ArrayList<String>();
  List<String> values = new ArrayList<String>();
  List<String> totalLogs = new ArrayList<String>();
  List<String> showLogs = new ArrayList<String>();
  
    public AdminController() {
        super();
        getFromDatabase();
    }
    
    public void getFromDatabase() {
       totalLogs.clear();
       List<String> fields1 = new ArrayList<String>();
       List<String> values1 = new ArrayList<String>();

       String res = ""; 
       try {
             Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
         } catch (Exception ex) {
             // handle the error
           System.out.println(ex.getMessage());
         }
       Connection conn = null;
       try {
         conn = DriverManager.getConnection(Config.getUrlMySQL());
  
         System.out.println("\nDatabase..\n");
         Statement stmt = conn.createStatement();
         String db_type = null;
         String db_value = null;
         String db_data = null;
    
         String query = "SELECT type, content, DateInserted FROM cvbuilder_logs ORDER BY log_id DESC;";
         stmt.executeQuery(query);
         ResultSet rs = stmt.getResultSet();
                    
         while(rs.next()){
           db_type = rs.getString("type");
           db_value = rs.getString("content");
           db_data = rs.getString("DateInserted");
           fields1.add(db_type);
           values1.add(db_value + "     " + db_data);
         }
       } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
       }
       for(int i = 0; i < values1.size(); i++) {
         String type = fields1.get(i);
         String value = values1.get(i);
         res += " ("+(i+1)+") "+ type + " : " + value +  " \n";
         totalLogs.add(" ("+(i+1)+") "+ type + " : " + value +  " \n");
         System.out.println("Type" + type);
       }
    }
    
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      PrintWriter out = response.getWriter();
      response.setContentType("text/plain");
      Gson gson = new Gson();
      out.append(gson.toJson(showLogs));  

  }
     
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
      String check = request.getParameter("texttosend");

    getFromDatabase();
    showLogs.clear();
      for(String str: totalLogs) {
        if(check.trim().contains("login") && str.trim().contains("login")) {
          showLogs.add(str);
        }
        if(check.trim().contains("registration") && str.trim().contains("registration")) {
          showLogs.add(str);
        }
        if(check.trim().contains("Resume1") && str.trim().contains("LatexResume")) {
          showLogs.add(str);
        }
        if(check.trim().contains("Resume2") && str.trim().contains("Late2xResume")) {
          showLogs.add(str);
        }
        if(check.trim().contains("Logout") && str.trim().contains("logout")) {
            showLogs.add(str);
          }
        
      }
      

   }
 
}



