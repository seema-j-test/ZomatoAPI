import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class NegativeTests {
	
	private String key = "a59c0b615d53a275141c71d1912c9937";
	private String basepath = "https://developers.zomato.com/api/v2.1/";
	
	/**
	 * Verify that the dailymenu API response fails.
	 * Description: The dailymenu with no or invalid res_id gets 400 - bad request response.
     */
	@Test
	void testDailymenu() {		
		baseURI = basepath;
		given().
			header("user-key", key).
			queryParam("res_id", "16774318").
		when().	
			get("/dailymenu").
		then().
			statusCode(400).
			log().body();
	}
	
	/**
	 * Verify that the search API response fails.
	 * Description: The search API without user-key or invalid user-key gets 403 - forbidden response.
     */
	@Test
	void testSearch() {		
		baseURI = basepath;
		given().
			header("user-key", "abc123").
		when().	
			get("/search").
		then().
			statusCode(403).
			log().body();
	}
	
	/**
	 * Verify that the restaurant API response fails.
	 * Description: The restaurant API with no input value for res_id gets 404 - not found response.
     */
	@Test
	void testRestaurant() {		
		baseURI = basepath;
		given().
			header("user-key", key).		
			queryParam("res_id", "").
		when().	
			get("/restaurant").
		then().
			statusCode(404).
			log().body();		
	}

}
