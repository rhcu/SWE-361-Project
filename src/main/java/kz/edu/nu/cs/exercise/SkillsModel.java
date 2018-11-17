package kz.edu.nu.cs.exercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SkillsModel extends Model{
	private String username = null;
	public SkillsModel(String username) throws SQLException {
		super("cvbuilder_skills");
		this.username = username;
	}
	private boolean exists() throws SQLException {
		List<String> fields = new ArrayList<String>();
		fields.add("username");
		List<String> values = new ArrayList<String>();
		values.add(this.username);
		ResultSet rs = this.findWhere(fields, values);
		
		if(rs == null) return false;
		return true;
	}
	public void addOrUpdate(String skills) throws Exception {
		
		List<String> fields = new ArrayList<String>();
		fields.add("content");
		List<String> values = new ArrayList<String>();
		values.add(skills);
		
		if(exists()) {
			List<String> whereFields = new ArrayList<String>();
			whereFields.add("username");
			List<String> whereValues = new ArrayList<String>();
			whereValues.add(this.username);
			this.update(fields, values, whereFields, whereValues);
		}else {
			fields.add("username");
			values.add(this.username);
			this.insert(fields, values);
		}
		
		
	}
}