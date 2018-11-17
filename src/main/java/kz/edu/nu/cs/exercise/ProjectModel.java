package kz.edu.nu.cs.exercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProjectModel extends Model{
	private String username = null;
	public ProjectModel(String username) throws SQLException {
		super("cvbuilder_project");
		this.username = username;
	}
	private boolean exists(String num) throws SQLException {
		List<String> fields = new ArrayList<String>();
		fields.add("username");
		fields.add("num");
		List<String> values = new ArrayList<String>();
		values.add(this.username);
		values.add(num);
		ResultSet rs = this.findWhere(fields, values);
		
		if(rs == null) return false;
		return true;
	}
	public void addOrUpdate(String num, String name, String url, 
			String dates, String description) throws Exception {
		
		List<String> fields = new ArrayList<String>();
		fields.add("name");
		fields.add("url");
		fields.add("dates");
		fields.add("description");
		List<String> values = new ArrayList<String>();
		values.add(name);
		values.add(url);
		values.add(dates);
		values.add(description);
		
		if(exists(num)) {
			List<String> whereFields = new ArrayList<String>();
			whereFields.add("username");
			whereFields.add("num");
			List<String> whereValues = new ArrayList<String>();
			whereValues.add(this.username);
			whereValues.add(num);
			this.update(fields, values, whereFields, whereValues);
		}else {
			fields.add("username");
			fields.add("num");
			values.add(this.username);
			values.add(num);
			this.insert(fields, values);
		}
		
		
	}
}
