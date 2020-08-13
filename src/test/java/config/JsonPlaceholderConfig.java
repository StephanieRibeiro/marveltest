package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class JsonPlaceholderConfig {

	public final String BASE_URI;
	
	public JsonPlaceholderConfig() throws FileNotFoundException, IOException {
		Properties config = new Properties();
		config.load(new FileInputStream("config.properties"));
		
		this.BASE_URI = config.getProperty("jsonplaceholder.base.uri");
	}
	
}
