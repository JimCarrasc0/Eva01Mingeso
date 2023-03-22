package com.mingeso.eva01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Eva01Application {

	public static void main(String[] args) {
		SpringApplication.run(Eva01Application.class, args);
	}

	@GetMapping("/")
	public String HelloWorld(){
		return "Hello World";
	}

}
