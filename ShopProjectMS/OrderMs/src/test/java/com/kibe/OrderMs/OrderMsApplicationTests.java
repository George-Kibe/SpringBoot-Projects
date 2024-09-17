package com.kibe.OrderMs;

import com.kibe.OrderMs.stubs.InventoryClientStub;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) // helps pick a random port
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
				    "skuCode": "iphone_15",
				    "price": 1560,
				    "quantity":1
				}
				""";
		InventoryClientStub.stubInventoryCall("iphone_15", 1);
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
