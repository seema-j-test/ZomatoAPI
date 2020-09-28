import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class RestaurantTest {
	
	private String key = "a59c0b615d53a275141c71d1912c9937";
	private String basepath = "https://developers.zomato.com/api/v2.1/";
	
	/**
	 * Get the restaurant details.
     * Description: Verify that the restaurant API retrieves the details of a required restaurant.
     */
	@Test
	void testRestaurant() {		
		baseURI = basepath;
		given().
			header("user-key", key).		
			queryParam("res_id", "16774318").
		when().	
			get("/restaurant").
		then().
			statusCode(200).
			log().body();		
	}
	
	/**
	 * Get the restaurant reviews.
     * Description: Verify that the reviews API retrieves the reviews of a required restaurantq.
     */	
	@Test
	void testReviews() {		
		baseURI = basepath;
		given().
			header("user-key", key).		
			queryParams("res_id", "16774318", "start", "5", "count", "1").
		when().	
			get("/reviews").
		then().
			statusCode(200);		
	}

}
