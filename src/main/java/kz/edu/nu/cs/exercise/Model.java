package kz.edu.nu.cs.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Model {
	private String tablename = null;
	private Connection conn = null;
	public Model(String tablename) throws SQLException {
		 try {
	         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	     } catch (Exception ex) {
	         // handle the errorr
	    	 System.out.println(ex.getMessage());
	     }
		this.tablename = tablename;
		this.conn = DriverManager.getConnection(Config.getUrlMySQL());
	}
	public ResultSet findWhere(List<String> fields, List<String> values) throws SQLException {
		Statement stmt = this.conn.createStatement();
        String db_username = null;
        String db_password = null;
        
        String where_clause = "";
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0)
				where_clause += " and ";
			where_clause += fields.get(i)+"='"+values.get(i)+"'";
		}
        String query = "SELECT * FROM "+this.tablename+" WHERE "+where_clause;
        System.out.println("Find where query: " + query);
        ResultSet rs = stmt.executeQuery(query);
        if(!rs.next())
        	return null;
        return rs;
	}
	public void update(List<String> fields, List<String> values,
			List<String> whereFields, List<String> whereValues) throws SQLException {
		String where_clause = "";
		for(int i = 0; i < whereFields.size(); i++) {
			if(i > 0)
				where_clause += " and ";
			where_clause += whereFields.get(i)+"='"+whereValues.get(i)+"'";
		}
		String set = "";
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0)
				set += ",";
			set += fields.get(i)+"='"+values.get(i)+"'";
		}
		String query = "UPDATE "+this.tablename+" SET " + set + " WHERE " + where_clause;
        System.out.println("Update query: " + query);
		PreparedStatement ps = conn.prepareStatement(query); // generates sql query
		ps.executeUpdate(); // execute it on test database
		System.out.println("successfuly updated");
		ps.close();
		conn.close();
	}
	public void insert(List<String> fields, List<String> values) throws Exception {
		if(fields.size() != values.size()) 
			throw new Exception("Fields and values arrays should be equal in size");
		String fields_str = "";
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0)
				fields_str += ",";
			fields_str += fields.get(i);
		}
		String values_str = "";
		for(int i = 0; i < fields.size(); i++) {
			if(i > 0)
				values_str += ",";
			values_str += "?";
		}
		String query = "insert into "+this.tablename+"("+fields_str+") values("+values_str+")";

        System.out.println("Insert query: " + query);
		PreparedStatement ps = conn.prepareStatement(query); // generates sql query
		for(int i = 0; i < values.size(); i++) {
			ps.setString(i + 1, values.get(i) );
		}
		ps.executeUpdate(); // execute it on test database
		System.out.println("successfuly inserted");
		ps.close();
		conn.close();
	}
}
