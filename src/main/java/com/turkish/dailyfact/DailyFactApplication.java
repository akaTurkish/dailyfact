package com.turkish.dailyfact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyFactApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyFactApplication.class, args);
	}

}
