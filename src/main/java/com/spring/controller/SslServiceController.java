package com.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentController;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.repository.SslEnvironmentRepository;

@RestController
public class SslServiceController extends EnvironmentController {

	@Value("${spring.application.name}")
	private String appName;

	private EnvironmentRepository repository;

	public SslServiceController(EnvironmentRepository repository, ObjectMapper objectMapper) {

		super(repository, objectMapper);
		this.repository = repository;

	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello from " + appName;
	}

	@GetMapping("/{application}/{profile}")
	public ResponseEntity<Environment> getSslProperties(@PathVariable("application") String application,
			@PathVariable("profile") String profile) {

			return new ResponseEntity<Environment>(this.repository.findOne(application, profile, null),HttpStatus.OK);
			
			
	}

}
