package com.example.TaskProject;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskProjectApplication.class, args);
	}
	
	@Bean
	ModelMapper modelMapper() {
			return new ModelMapper();
	}

}
