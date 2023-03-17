package com.debugdemons.bimdb;

import com.debugdemons.bimdb.utils.LoggerProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class BimdbApplication implements LoggerProvider {

	public static void main(String[] args) {
		SpringApplication.run(BimdbApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		getLogger().debug("Hello World");
		return "Hello World ";
	}
}
