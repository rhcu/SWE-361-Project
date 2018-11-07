package kz.edu.nu.cs.exercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LogModel extends Model{
	private String username = null;
	public LogModel() throws SQLException {
		super("cvbuilder_logs");
	}
	public void add(String type, String content) throws Exception {
		
		List<String> fields = new ArrayList<String>();
		fields.add("type");
		fields.add("content");
		List<String> values = new ArrayList<String>();
		values.add(type);
		values.add(content);
		this.insert(fields, values);
		
	}
}
