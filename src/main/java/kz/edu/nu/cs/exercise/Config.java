package kz.edu.nu.cs.exercise;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Config {
	/*private static String host = "localhost";
	private static String database = "cv";
	private static String user = "root";
	private static String password = "";
	*/
	private static String host = "10.10.3.14";
	private static String database = "testdatabase361";
	private static String user = "user361";
	private static String password = "secret";
	
	
	public static String getUrlMySQL() {
		return "jdbc:mysql://"+host+"/"+database+"?" +
                "user="+user+"&password="+password+"&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	}
	public static String md5(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String myHash = new String(digest, StandardCharsets.UTF_8);
		return myHash;
	}
}
