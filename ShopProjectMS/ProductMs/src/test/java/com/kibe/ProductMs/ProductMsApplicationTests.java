package com.kibe.ProductMs;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductMsApplicationTests {
	@ServiceConnection // adds default mongodb config under application.properties
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

	@LocalServerPort // gets the current port
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI="http://localhost:"+ port;
		// RestAssured.port = port;
	}
	static {
		mongoDBContainer.start(); // starts database before doing the tests
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				    "name": "Iphone 16 pro",
				    "description": "This is just some random test",
				    "price":1000
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Iphone 16 pro"))
				.body("description", Matchers.equalTo("This is just some random test"))
				.body("price", Matchers.equalTo(1000));
	}

}
