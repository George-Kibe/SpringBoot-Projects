package com.kibe.InventoryMs;

import org.springframework.boot.SpringApplication;

public class TestInventoryMsApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryMsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
