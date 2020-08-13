package testCases.positives;

import config.JsonPlaceholderConfig;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class PostNewUserTestCase {

	private JsonPlaceholderConfig config;
	
	@Before
	public void setUp() throws IOException {
		this.config = new JsonPlaceholderConfig();
	}
	
	@Test
	public void testMain() {
		Map<String,String> user = new HashMap<>();
		user.put("userId", "1");
		user.put("title", "Katlim");
		user.put("body", "Amigas para sempre!");
		
		given()
			.baseUri(config.BASE_URI)
			.accept("application/json")
			.contentType("application/json")
			.body(user)
			
		.when()
			.post("/posts")
			
		.then()
			.log().all()
			.statusCode(HttpStatus.SC_CREATED)
			.body("id", greaterThan(100));
	}
// FUCKING YEAH. //
}
