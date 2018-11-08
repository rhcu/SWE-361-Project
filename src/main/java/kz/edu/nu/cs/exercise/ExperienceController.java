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
@WebServlet(urlPatterns = { "/experience" })
public class ExperienceController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(username != null) {
			String method = request.getParameter("method");
			if(method == null || method.equals("post")) {
				try {
					for(int i = 1; i < 100; i++) {
						 String num = Integer.toString(i);
						 String title = request.getParameter("title-" + num);
						 
						 String company = request.getParameter("company-" + num);
						 String dates = request.getParameter("dates-" + num);
						 String description = request.getParameter("description-" + num);
						 String location = request.getParameter("location-" + num);
						 
						 if(title!=null && company!=null && dates!=null 
								 && description!=null && location!=null &&
								 title.length() > 0 && company.length() > 0 && dates.length() > 0 
								 && description.length() > 0 && location.length() > 0) {
							 ExperienceModel model = new ExperienceModel(username);
							 model.addOrUpdate(num, title, company, dates, location, description);
						 }
					 }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(method.equals("delete")){
				String num = request.getParameter("num");
				if(num != null) {
					ExperienceModel model;
					try {
						model = new ExperienceModel(username);
						model.deleteNum(num);
					} catch (SQLException e) {
						e.printStackTrace();
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
			for(int i = 1; i < 100; i++) {
				 JSONObject obj = new JSONObject();
				 
				 String num = Integer.toString(i);
				 ExperienceModel model = null;
				 try {
					model = new ExperienceModel(username);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 List<String> fields = new ArrayList<String>();
				 fields.add("username");
				 fields.add("num");
				 List<String> values = new ArrayList<String>();
				 values.add(username);
				 values.add(num);
				 try {
					ResultSet rs = model.findWhere(fields, values);
					if(rs!= null) {
						obj.put("title-"+num, rs.getString("title"));
						obj.put("company-"+num, rs.getString("company"));
						obj.put("dates-"+num, rs.getString("dates"));
						obj.put("description-"+num, rs.getString("description"));
						obj.put("location-"+num, rs.getString("location"));
						
						arr.add(obj);
					}
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
	
