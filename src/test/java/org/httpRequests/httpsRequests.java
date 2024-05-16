package org.httpRequests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

import org.testng.annotations.Test;
public class httpsRequests {

	public int ida;
	@Test (priority = 0)
	public void getusers() {
		
		//https://reqres.in/api/users?page=1
		given().
		when().get("https://reqres.in/api/users?page=1")
		.then()
		.statusCode(200)
		.body("page", equalTo(1))
		
		.log().all();
		
		
	}
	
	@Test
	public void createUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "balaji");
		data.put("job", "trainer");
		ida = given()
				.contentType("application/json")
				.body(data)
				.when().post("https://reqres.in/api/users")
				.jsonPath().getInt("id");
	}
	@Test(dependsOnMethods = "createUser")
	public void UpdateUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "purnima");
		data.put("job", "teacher");
		given()
				.contentType("application/json")
				.body(data)
				.when().put("https://reqres.in/api/users/"+ida)
				.then()
				.statusCode(200)
				.log().all();
				
	}
	
	
	@Test (dependsOnMethods= "UpdateUser")
	public void deleteUser() {
		given()
		.when().delete("https://reqres.in/api/users/"+ ida)
		.then()
			.statusCode(204).log().all();
	}
	
	
}
