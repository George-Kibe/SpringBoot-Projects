package com.kibe.OrderMs;

import org.springframework.boot.SpringApplication;

public class TestOrderMsApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderMsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
