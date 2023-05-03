package com.debugdemons.bimdb;

import com.debugdemons.bimdb.utils.LoggerProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class BimdbApplication implements LoggerProvider {

	public static void main(String[] args) {
		SpringApplication.run(BimdbApplication.class, args);
	}

}
