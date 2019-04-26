package com.min.valid.config;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SecurityConfigTest {

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
	}
	
	@Test
	public void test() {
		given()
				.when()
					.get("/")
				.then()
					.statusCode(200)
//					.header("Location", containsString("http://localhost"))
				;
	}

}
