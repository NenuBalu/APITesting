package org.Pojo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class postReqUsingPOJO {
	@Test
	public void postUsingPojo() {
		pojoData pojo = new pojoData();
		pojo.setName("balu");
		pojo.setId("5");
		pojo.setLocation("hyd");
		pojo.setPhone("566577585");
		String coursesArr[] = {"C", "Python" , "C++" , "Java"};
		pojo.setCourses(coursesArr);
		
		given()
			.contentType("application/json")
			.body(pojo)
		.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("balu"))
		.body("location", equalTo("hyd"))
		.body("courses[0]" , equalTo("C"))
		.header("Content-Type", "application/json")
		.log().all();
		
	}
	
	@Test(dependsOnMethods = "postUsingPojo")
	public void hashMapDelete() {
		given()
		.when().delete("http://localhost:3000/students/5")
		.then().statusCode(200);
	}
}
