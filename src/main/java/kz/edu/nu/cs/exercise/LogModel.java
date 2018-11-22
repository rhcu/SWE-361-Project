package kz.edu.nu.cs.exercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
  fields.add("DateInserted");
  List<String> values = new ArrayList<String>();
  DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currDate = LocalDateTime.now();
        String dates = "" + date.format(currDate);
  values.add(type);
  values.add(content);
        values.add(dates);
  this.insert(fields, values);
  
 }
}