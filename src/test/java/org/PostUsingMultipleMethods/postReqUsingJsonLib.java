package org.PostUsingMultipleMethods;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class postReqUsingJsonLib {
	
	@Test
	public void postUsingJsonLib() {
		
		JSONObject data = new JSONObject();
		data.put("id", "4");
		data.put("name", "Scott");
		data.put("location", "Hyd");
		data.put("phone", "5457778895");
		String CourseArray[]= { "C" , "C++"};
		data.put("courses", CourseArray);
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("Hyd"))
			.body("courses[0]" , equalTo("C"))
			.header("Content-Type", "application/json")
			.log().all();
	}
	@Test (dependsOnMethods = "postUsingJsonLib")
	public void hashMapDelete() {
		given()
		.when().delete("http://localhost:3000/students/4")
		.then().statusCode(200);
	}
}
