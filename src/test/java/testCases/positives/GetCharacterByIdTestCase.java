package testCases.positives;

import config.MarvelConfig;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetCharacterByIdTestCase {

	private static final String SPIDER_MAN_ID = "1009610";
	
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

		.when()
			.pathParam("characterId", SPIDER_MAN_ID)
			.get("/v1/public/characters/{characterId}")
			
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body("data.count", equalTo(1))
			.body("data.results[0].name", equalTo("Spider-Man"));
	}

}
