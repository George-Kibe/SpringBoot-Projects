package com.kibe.NotificationMs;

import org.springframework.boot.SpringApplication;

public class TestNotificationMsApplication {

	public static void main(String[] args) {
		SpringApplication.from(NotificationMsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
