package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

public class MarvelConfig {

	public final String BASE_URI;
	public final String PUBLIC_API_KEY;
	public final String PRIVATE_API_KEY;
	
	public MarvelConfig() throws FileNotFoundException, IOException {
		Properties config = new Properties();
		config.load(new FileInputStream("config.properties"));
		
		this.BASE_URI = config.getProperty("marvel.api.base.uri");
		this.PUBLIC_API_KEY = config.getProperty("marvel.api.public.key");
		this.PRIVATE_API_KEY = config.getProperty("marvel.api.private.key");
	}
	
	/**
	 * TODO Explicar o que esse metodo faz.
	 * 
	 * @return the actual timestamp as a string.
	 */
	public String getTimestamp() {
		Date today = new Date();
		long time = today.getTime();
		Timestamp timestamp = new Timestamp(time);
	    return timestamp.toString();
	}
	
	/**
	 * TODO Explicar o que esse metodo faz.
	 * Fonte: http://pad.yohdah.com/101/python-vs-java-md5-hexdigest
	 * 
	 * @param timestamp is the actual timestamp.
	 * @return a hash as a string.
	 * @throws NoSuchAlgorithmException if the md5 method fails.
	 */
	public String getHash(String timestamp) {
		try {
			String message = timestamp + PRIVATE_API_KEY + PUBLIC_API_KEY;
	        String hd;
	        MessageDigest md5;
			md5 = MessageDigest.getInstance( "MD5" );
	        md5.update( message.getBytes() );
	        BigInteger hash = new BigInteger( 1, md5.digest() );
	        hd = hash.toString(16); // BigInteger strips leading 0's
	        while( hd.length() < 32 ) { hd = "0" + hd; } // pad with leading 0's
	        return hd;
		} catch(NoSuchAlgorithmException ex) {
			throw new RuntimeException("A hash ficou zuada.", ex);
		}
    }

}
