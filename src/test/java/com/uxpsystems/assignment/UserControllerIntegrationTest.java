package com.uxpsystems.assignment;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uxpsystems.assignment.controller.UserController;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.model.UserStatus;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;


@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.uxpsystems.assignment")
//@EnableAutoConfiguration
@ContextConfiguration(locations={"classpath*:spring-context.xml"})
public class UserControllerIntegrationTest {

	private static final String ADMIN_USER_NAME = "admin";
	private static final String ADMIN_PASSWORD = "password";
	private static String pathOfURL;

	@BeforeClass
	public static void setUp() throws InterruptedException{
		SpringApplication.run(UserController.class, "");

		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/user";
		RestAssured.authentication = basic(ADMIN_USER_NAME, ADMIN_PASSWORD);
		pathOfURL = RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath;
		//2 seconds sleep so that in-memory database starts correctly
		Thread.sleep(2000);
	}

	@AfterClass
	public static void destroy() throws InterruptedException{
		//2 seconds sleep so that in-memory database shutdown correctly
		Thread.sleep(2000);
	}

	String userJsonRequest = "{\"userid\": \"1\",\"username\": \"Nagayya\",\"password\": \"testpassword\",\"status\": \"Activated\"}";
	String userJsonResponse = "{\"userid\": \"[\\d]+\",\"username\": \"[\\p{ASCII}]+\",\"password\": \"[\\p{ASCII}]+\",\"status\": \"Activated\"}";

	public static Response response;

	ResponseSpecification checkStatusCodeAndContentType = 
			new ResponseSpecBuilder().
			expectStatusCode(200).
			expectContentType(ContentType.JSON).
			build();

	@Test
	public void checkUserHTMLPage() throws InterruptedException {

		given().
		when().
			get(RestAssured.baseURI + ":" + RestAssured.port + "/uxpuser.html").
		then().
				assertThat().
				statusCode(200).
			and().
				contentType(ContentType.HTML);

	}

	@Test
	public void checkWrongHTMLPage() throws InterruptedException {

		given().
		when().
			get(RestAssured.baseURI + ":" + RestAssured.port + "/wronguxpuser.html").
		then().
				assertThat().
				statusCode(404);

	}

	@Test
	public void checkCreateUserResponse() throws InterruptedException {

		String username = "checkCreateUserAPI";
		String userJsonRequest = "{\"userid\": \"1\",\"username\": \"" + username + "\",\"password\": \"testpassword\",\"status\": \"Activated\"}";

		response =
				given().
					body(userJsonRequest).
					contentType(ContentType.JSON).
				when().
					post().
				then().
					assertThat().
					spec(checkStatusCodeAndContentType).
				extract().
					response();

		JsonPath jsonPath = new JsonPath(response.asString());
		assertTrue(username.equals(jsonPath.get("username")));
		assertTrue("Activated".equals(jsonPath.get("status")));

	}


	@Test
	public void CreateAndThenGetUser() throws InterruptedException {

		String username = "checkBasicAuthenticationForGetUserAPI";
		String userJsonRequest = "{\"userid\": \"1\",\"username\": \"" + username + "\",\"password\": \"testpassword\",\"status\": \"Activated\"}";

		given().
			body(userJsonRequest).
			contentType(ContentType.JSON).
		when().
			post().
		then().
			assertThat().
			spec(checkStatusCodeAndContentType);

		response =
				given().
				when().
					get(pathOfURL + "?username=" + username).
				then().
					assertThat().
					spec(checkStatusCodeAndContentType).
				extract().
					response();

		JsonPath jsonPath = new JsonPath(response.asString());
		assertTrue(username.equals(jsonPath.get("username")));
		assertTrue("Activated".equals(jsonPath.get("status")));

	}


	@Test
	public void checkCreateAndThenDeleteUser() throws InterruptedException {

		String username = "checkBasicAuthenticationForDeleteUserAPI";
		String userJsonRequest = "{\"userid\": \"1\",\"username\": \"" + username + "\",\"password\": \"testpassword\",\"status\": \"Activated\"}";

		given().
			body(userJsonRequest).
			contentType(ContentType.JSON).
		when().
			post().
		then().
			assertThat().
			spec(checkStatusCodeAndContentType);

		response =
				given().
				when().
					delete(pathOfURL + "?userid=" + 1).
				then().
					assertThat().
					spec(checkStatusCodeAndContentType).
				extract().
					response();

		JsonPath jsonPath = new JsonPath(response.asString());
		assertTrue("Deactivated".equals(jsonPath.get("status")));

	}

	@Test
	public void checkCreateUserThenUpdateUser() throws InterruptedException {



		String username = "checkBasicAuthenticationForDeleteUserAPI";
		String usernameupdated = "checkBasicAuthenticationForDeleteUserAPIUPDATE";
		String userJsonRequest = "{\"userid\": \"1\",\"username\": \"" + username + "\",\"password\": \"testpassword\",\"status\": \"Activated\"}";
		String userJsonRequestUpdated = "{\"userid\": \"1\",\"username\": \"" + usernameupdated + "\",\"password\": \"testpassword\",\"status\": \"Activated\"}";

		response =
			given().
				body(userJsonRequest).
				contentType(ContentType.JSON).
			when().
				post().
			then().
				assertThat().
				spec(checkStatusCodeAndContentType).
			extract().
				response();

		JsonPath jsonPath = new JsonPath(response.asString());
		User updatedUser = new User();
		updatedUser.setUserid(jsonPath.getInt("userid"));
		updatedUser.setUsername(usernameupdated);
		updatedUser.setPassword((String)jsonPath.get("password"));
		updatedUser.setStatus(UserStatus.Activated);
		
		userJsonRequestUpdated = userJsonRequest.replace("\"1\"", "\"" + jsonPath.getInt("userid") + "\"");
		userJsonRequestUpdated = userJsonRequestUpdated.replace(username, usernameupdated);
		
		response =
				given().
					body(userJsonRequestUpdated).
					contentType(ContentType.JSON).
				when().
					put().
				then().
					assertThat().
					spec(checkStatusCodeAndContentType).
				extract().
					response();

		JsonPath jsonPathNew = new JsonPath(response.asString());
		assertTrue(usernameupdated.equals(jsonPathNew.get("username")));
		assertTrue("Activated".equals(jsonPathNew.get("status")));

	}

	@Test
	public void checkAuthenticationFailureForAllUserAPI() throws InterruptedException {

		given().
			auth().
			preemptive().
			basic("username", "password").
		when().
			get().
		then().
			assertThat().
			statusCode(401);

		given().
			auth().
			preemptive().
			basic("username", "password").
		when().
			post().
		then().
			assertThat().
			statusCode(401);

		given().
			auth().
			preemptive().
			basic("username", "password").
		when().
			put().
		then().
			assertThat().
			statusCode(401);

		given().
			auth().
			preemptive().
			basic("username", "password").
		when().
			delete().
		then().
			assertThat().
			statusCode(401);
		
	}

}
