import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LocationsTest {
	
	private String key = "a59c0b615d53a275141c71d1912c9937";
	private String basepath = "https://developers.zomato.com/api/v2.1/";

	/**
	 * Search for Zomato locations by keyword.
     * Description: Verify that the locations API retrieves a location matching the given "city_id" and a "city_name".
     */
	@Test
	void testLocations() {
		baseURI = basepath;
		given().
			header("user-key", key).	
			queryParam("query", "New York City").
			queryParams("lat", "40.742051", "lon", "-74.004821"). //Optional. Provide co-ordinates for better search results. 
		when().
			get("/locations").
	    then().
			statusCode(200).
			body("location_suggestions.city_id[0]", equalTo(280)).
			body("location_suggestions.city_name", hasItems("New York City")).
			log().body();
	}
	
	/**
	 * Get Zomato location details.
     * Description: Verify that the locations_details API retrieves the location details matching the given "entity_id" and a "entity_type".
     */
	@Test
	void testLocationdetails() {
		baseURI = basepath;
		given().
			header("user-key", key).
			queryParam("entity_id", "36932").
			queryParam("entity_type", "group").
		when().	
			get("/location_details").
	    then().
			statusCode(200);
	}
	
	/**
	 * Get a list of cities matching the query.
     * Description: Verify that the cities API retrieves a city matching the given "state_id".
     */
	@Test
	void testCities() {
		baseURI = basepath;
		given().
			header("user-key", key).	
			queryParams("q", "New York City", "lat", "40.742051", "lon", "-74.004821", "count", "1").
		when().	
			get("/cities").
		then().
			statusCode(200).
			body("location_suggestions.state_id[0]", equalTo(103)).
			log().body();
	}
	
	/**
	 * Get location details based on co-ordinates.
     * Description: Verify that the geocode API lists the top cuisines and nearby restaurants around the given coordinates.
     */
	@Test
	void testGeocode() {
		baseURI = basepath;
		given().
			header("user-key", key).
			queryParams("lat", "40.742051", "lon", "-74.004821").
		when().	
			get("/geocode").
	    then().
			statusCode(200);
	}

}
