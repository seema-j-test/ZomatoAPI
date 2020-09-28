import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.testng.annotations.Test;

public class FeaturesTest {
	
	private String key = "a59c0b615d53a275141c71d1912c9937";
	private String basepath = "https://developers.zomato.com/api/v2.1/";
	
	/**
	 * Get list of all categories.
     */
	@Test
	void testCategory() {
		baseURI = basepath;
		given().
			header("user-key", key).
		when().
			get("/categories").
		then().
			statusCode(200).
			log().status();	
	}
	
	/**
	 * Get a collection of all Ramen restaurants in a given location.
     * Description: Verify that the collections API retrieves a collection matching the "collection_id" = 35 and has a "title" = "Ramen".
     */
	@Test
	void testCollection() {		
		baseURI = basepath;
		given().
			header("user-key", key).
			queryParams("city_id", "280", "count", "1").
		when().	
			get("/collections").
	    then().
	        statusCode(200).
			body("collections.collection.collection_id[0]", equalTo(35)).
			body("collections.collection.title", hasItems("Ramen")).
			log().body();
	}
	
	/**
	 * Get a list of all cuisines in a city.
     * Description: Verify that the cuisines API retrieves a list of all cuisines matching the given co-ordinates.
     */
	@Test
	void testCuisines() {
		baseURI = basepath;
		given().
			header("user-key", key).	
			queryParams("city_ids", "280", "lat", "40.742051", "lon", "-74.004821").
		when().	
			get("/cuisines").
		then().
			statusCode(200);
	}
	
	/**
	 * Search for restaurants.
     * Description: Verify that the search API retrieves a list of matching restaurants.
     */
	@Test
	void testSearch() {		
		baseURI = basepath;
		given().
		header("user-key", key).
		 queryParams("entity_id", "36932", "entity_type", "group", "q", "New York City, NY", "start", "5", "count", "1", "lat", "40.742051", "lon", "-74.004821", "radius", "100", "cuisines", "1,3,7", "establishment_type", "1", "collection_id", "1", "category_id", "1","sort", "rating", "order", "asc").
		when().	
			get("/search").
		then().
			statusCode(200);		
	}
	
	/**
	 * Get a list of Establishment types in a city.
     * Description: Verify that the establishments API retrieves a list of all establishment types for given co-ordinates.
     */
	@Test
	void testEstablishments() {		
		baseURI = basepath;
		given().
			header("user-key", key).
			queryParams("city_ids", "280","lat", "40.742051", "lon", "-74.004821").
		when().	
				get("/establishments").
		then().
			statusCode(200);		
	}

}
