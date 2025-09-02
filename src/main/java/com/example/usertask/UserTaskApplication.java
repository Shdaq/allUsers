package com.example.usertask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class UserTaskApplication {

	public static void main(String[] args)  {

		ApplicationContext context= SpringApplication.run(UserTaskApplication.class, args);

	}

}
