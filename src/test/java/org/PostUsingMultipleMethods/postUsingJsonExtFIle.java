package org.PostUsingMultipleMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


public class postUsingJsonExtFIle {
	
	public FileReader fr;
	@Test
	public void postusingjson() {
		File fi = new File(".\\body.json");
		
		try {
			fr = new FileReader(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		
		given()
		.contentType("application/json")
		.body(data.toString())
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
	@Test(dependsOnMethods = "postusingjson")
	public void deleteUser() {
		given()
		.when().delete("http://localhost:3000/students/5")
		.then().statusCode(200);
	}
}
