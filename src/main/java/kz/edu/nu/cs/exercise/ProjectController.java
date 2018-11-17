package kz.edu.nu.cs.exercise;
import java.sql.DriverManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.json.simple.*;
/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet(urlPatterns = { "/project" })
public class ProjectController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 LogModel log = null;
	     try {
	      log = new LogModel();
	     } catch (SQLException e) {
	      e.printStackTrace();
	     }    
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username != null) {
			String method = request.getParameter("method");
			if(method == null || method.equals("post")) {
				ProjectModel model = null;
				try {
					for(int i = 1; i < 100; i++) {
						 String num = Integer.toString(i);
						 String name = request.getParameter("name-" + num);
						 
						 String url = request.getParameter("url-" + num);
						 String dates = request.getParameter("dates-" + num);
						 String description = request.getParameter("description-" + num);
						 
						 if(name!=null && url!=null && description!=null && dates!=null 
								 && name.length() > 0 && url.length() > 0 && dates.length() > 0 
								 && description.length() > 0) {
							 model = new ProjectModel(username);
							 model.addOrUpdate(num, name, url, dates, description);
						 }
          }
				  log.add("Project",  "Project successfully generated");
				} catch (Exception e) {
              try {
						    log.add("Project",  "Project failed to generate");
						  } catch (Exception LogExc) {
							  e.printStackTrace();
						  }
						  e.printStackTrace();
				}finally {
					try {
						model.disconnect();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}else if(method.equals("delete")){
				String num = request.getParameter("num");
				ProjectModel model = null;
				if(num != null) {
					try {
						model = new ProjectModel(username);
						model.deleteNum(num);
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						try {
							model.disconnect();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		JSONArray arr = new JSONArray();
		if(username != null) {
				 
				 //String num = Integer.toString(i);
				ProjectModel model = null;
				 try {
					model = new ProjectModel(username);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 List<String> fields = new ArrayList<String>();
				 fields.add("username");
				 //fields.add("num");
				 List<String> values = new ArrayList<String>();
				 values.add(username);
				 //values.add(num);
				 try {
					ResultSet rs = model.findWhere(fields, values);
					//if(rs!= null) {
						while(rs != null) {

							JSONObject obj = new JSONObject();
							String num = rs.getString("num");
							obj.put("name-"+num, rs.getString("name"));
							obj.put("url-"+num, rs.getString("url"));
							obj.put("dates-"+num, rs.getString("dates"));
							obj.put("description-"+num, rs.getString("description"));
							
							arr.add(obj);
							rs.next();
						}
					//}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						model.disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
		String result = arr.toJSONString();
		ServletOutputStream out = response.getOutputStream();
		out.write(result.getBytes());
	}
}
	
