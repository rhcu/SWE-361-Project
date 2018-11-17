package kz.edu.nu.cs.exercise;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


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
	
	private static final String ALGO = "AES";
	  private static final byte[] keyValue =
	              new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};

	  private static Key generateKey() throws Exception {
	      return new SecretKeySpec(keyValue, ALGO);
	  }
	  
	  

	  public static String encrypt(String data) throws Exception {
	      Key key = generateKey();
	      Cipher c = Cipher.getInstance(ALGO);
	      c.init(Cipher.ENCRYPT_MODE, key);
	      byte[] encVal = c.doFinal(data.getBytes());
	      return Base64.getEncoder().encodeToString(encVal);
	  }
	
	  public static String md5(String pass) {
		    
		    String strI;
		    try {
		      strI = encrypt(pass);
		    } catch (Exception e) {
		      // TODO Auto-generated catch block
		      strI = "Error";
		      e.printStackTrace();
		    }

		    return strI;

	}
	  
	
	//public static String md5(String pass) throws NoSuchAlgorithmException {
		//return pass + "enc";
		/*
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] digest = md.digest();
		String myHash = new String(digest, StandardCharsets.UTF_8);
		return myHash;
		*/
	//}
}
