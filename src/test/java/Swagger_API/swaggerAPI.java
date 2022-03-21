package Swagger_API;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class swaggerAPI {
	
	@Test(enabled = true)
	public void createUser(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("username", "Sumit001");
		obj.put("firstName", "Sumit");
		obj.put("lastName", "Kumar");
		obj.put("email", "sk8052@yahoo.com");
		obj.put("password", "qwerty123");
		obj.put("phone", "8987107870");
		
		String u_name="Sumit001";
		
//		System.out.println(obj.toJSONString());
		
		
		given()
		.contentType(ContentType.JSON)
		.body(obj.toJSONString()).
	when()
		.post("/user").
	then()
		.statusCode(200)
		.log()
		.all();
		
		val.setAttribute("username", u_name);
	}
	
	@Test(enabled = true, dependsOnMethods="createUser")
	public void login()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().
		when()
			.queryParam("username","Sumit001")
			.queryParam("password","qwerty123")
			.get("/user/login").
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled = true, dependsOnMethods= "login")
	public void edit(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		JSONObject obj = new JSONObject();
		obj.put("username", "Semantoo123");
		obj.put("firstName", "Semantoo");
		obj.put("lastName", "Sahni");
		obj.put("email", "ssini@bob.com");
		obj.put("password", "asdfgh123");
		obj.put("phone", "9876543210");
		
		String u_name="Semantoo123";
		
//		System.out.println(obj.toJSONString());
		
		given()
		.contentType(ContentType.JSON)
		.body(obj.toJSONString()).
	when()
		.put("/user/"+val.getAttribute("username")).
	then()
		.statusCode(200)
		.log()
		.all();
//		System.out.println(val.getAttribute("username"));
		val.setAttribute("username", u_name);
		
	}
	
	@Test(enabled = true, dependsOnMethods= "edit")
	public void logout()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		given().
		when()
			.get("/user/logout").
		then()
			.statusCode(200)
			.log()
			.all();
	}
	
	@Test(enabled = true, dependsOnMethods="logout")
	public void delete(ITestContext val)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
//		System.out.println(obj.toJSONString());
//		System.out.println(val.getAttribute("username"));

		
		given().
		when()
		.delete("/user/"+val.getAttribute("username").toString()).
	then()
		.statusCode(200)
		.log()
		.all();
		

	}
	
}
