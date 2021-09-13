package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);

		getJSON();
	}

	@PostConstruct
	public void init()
	{
		Logger log = LoggerFactory.getLogger(DemoApplication.class);
		log.info("Java app started");
	}


	@GetMapping("/")
	public static Map<String, String> getJSON() {
		HashMap<String, String> map = new HashMap<>();
		map.put("id", "05b03105-5a90-4d1e-938c-51c1e80c9ed3");
		map.put("username", "this is Spring Boot");
		return map;
	}
}
