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
@WebServlet(urlPatterns = { "/skills" })
public class SkillsController extends HttpServlet {
	
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
				SkillsModel model = null;
				try {
						String skills = request.getParameter("skills");
						 
						 if(skills!=null && skills.length() > 0) {
							 model = new SkillsModel(username);
							 model.addOrUpdate(skills);
						 }
				
				  log.add("Skills",  "Skills successfully generated");
				} catch (Exception e) {
              try {
						    log.add("Skills",  "Skills failed to generate");
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
				SkillsModel model = null;
				if(num != null) {
					try {
						model = new SkillsModel(username);
						model.deleteUsername(username);
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
		JSONObject obj = new JSONObject();
		if(username != null) {
				 
				SkillsModel model = null;
				 try {
					model = new SkillsModel(username);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 List<String> fields = new ArrayList<String>();
				 fields.add("username");
				 List<String> values = new ArrayList<String>();
				 values.add(username);
				 try {
					ResultSet rs = model.findWhere(fields, values);
					//if(rs!= null) {
						while(rs != null) {
							obj.put("skills", rs.getString("content"));
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
		String result = obj.toJSONString();
		ServletOutputStream out = response.getOutputStream();
		out.write(result.getBytes());
	}
}