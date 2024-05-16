package org.PostUsingMultipleMethods;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

import org.testng.annotations.Test;
public class hashMap {

	public int id;
	//@Test
	public void hashMapPost() {
		HashMap<Object, Object> hm = new HashMap<>();
		hm.put("name", "Scott");
		hm.put("location", "Hyd");
		hm.put("phone", "5457778895");
		String CourseArray[]= { "C" , "C++"};
		hm.put("courses", CourseArray);
		given()
			.contentType("application/json")
			.body(hm)
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
	@Test
	public void hashMapDelete() {
		given()
		.when().delete("http://localhost:3000/students/5")
		.then().statusCode(200);
	}
}
