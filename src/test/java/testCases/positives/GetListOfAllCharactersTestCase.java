package testCases.positives;

import config.MarvelConfig;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class GetListOfAllCharactersTestCase {

	private static final int EXPECTED_TOTAL = 1491;
	private MarvelConfig config;
	
	@Before
	public void setUp() throws IOException {
		this.config = new MarvelConfig();
	}
	
	@Test
	public void testMain() {
		String timestamp = config.getTimestamp();
		String hash = config.getHash(timestamp);
		
		given()
			.baseUri(config.BASE_URI)
			.accept("application/json")

			// Parametros obrigatorios de autenticacao da API da Marvel
			.param("ts", timestamp)
			.param("apikey", config.PUBLIC_API_KEY)
			.param("hash", hash)
			
			// Debug
			.log().all() 
			
		.when()
			.get("/v1/public/characters")
			
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body("data.total", equalTo(EXPECTED_TOTAL));
	}

}
