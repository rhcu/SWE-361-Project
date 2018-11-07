package kz.edu.nu.cs.exercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExperienceModel extends Model{
	private String username = null;
	public ExperienceModel(String username) throws SQLException {
		super("cvbuilder_experience");
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
	public void addOrUpdate(String num, String title, String company,
							String dates,String location, String description) throws Exception {
		
		List<String> fields = new ArrayList<String>();
		fields.add("title");
		fields.add("company");
		fields.add("dates");
		fields.add("location");
		fields.add("description");
		List<String> values = new ArrayList<String>();
		values.add(title);
		values.add(company);
		values.add(dates);
		values.add(location);
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
