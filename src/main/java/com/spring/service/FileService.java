package com.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	@Value("${configLocation}")
	private String configLocation;
	
	public PropertySource getProperties(String application, String profile) {
		// TODO Auto-generated method stub
		
		Map<String, String> map = new Hashtable<>();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(
					configLocation + application + "/" + profile + "/" + "certs.properties"));

			Properties props = new Properties();
			props.load(in);
			
			props.forEach((k, v) -> {
				map.put((String) k, (String) v);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("!"+map.toString());
		PropertySource source=new PropertySource("test", map);
		System.out.println("$"+source.getSource().toString());
		return source;

	}

}
