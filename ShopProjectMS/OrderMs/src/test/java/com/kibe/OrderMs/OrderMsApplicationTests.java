package com.kibe.OrderMs;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderMsApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port ;
	}
	static {
		mySQLContainer.start();
	}
	@Test
	void shouldPlaceOrder() {
		String placeOrderJson = """
				{
				    "skuCode": "Iphbebfuibefuybeyufb",
				    "price": 1560,
				    "quantity":4
				}
				""";
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(placeOrderJson)
				.when()
				.post("/api/orders")
				.then()
				.log().all()
				.statusCode(201)
				.extract().body().asString();

		assertThat(responseBodyString, Matchers.is("Order Placed successfully!"));
	}

}
