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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet(urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {

 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   HttpSession session = request.getSession();
   LogModel log = null;
   try {
    log = new LogModel();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
   try {
	   System.out.println("LOGOUT: " + request.getParameter("username"));
  log.add("logout", session.getAttribute("username") + " successfuly logged out");
 } catch (Exception e) {
  // TODO Auto-generated catch block
  e.printStackTrace();
 }
   session.removeAttribute("username");
   RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
      rd.forward(request, response);
 }
}
