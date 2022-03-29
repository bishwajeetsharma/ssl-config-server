package com.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.service.FileService;

@Repository
public class SslEnvironmentRepository implements EnvironmentRepository {

	@Autowired
	private FileService fileService;

	@Override
	public Environment findOne(String application, String profile, String label) {

		Environment environment = new Environment(application, profile);

		environment.add(fileService.getProperties(application, profile));
		return environment;
	}

	
}
