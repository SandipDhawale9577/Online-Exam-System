package com.example.onlineexamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@EntityScan("com")
@SpringBootApplication
public class OnlineexamprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineexamprojectApplication.class, args);
		System.out.println("started");
	}

}
