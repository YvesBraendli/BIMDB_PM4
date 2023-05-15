package com.debugdemons.bimdb.config;

import com.debugdemons.bimdb.interceptors.MovieDbApiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

	private final MovieDBApiConfig movieDBApiConfig;

	public RestTemplateConfig(MovieDBApiConfig movieDBApiConfig) {
		this.movieDBApiConfig = movieDBApiConfig;
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(List.of(new MovieDbApiInterceptor(movieDBApiConfig)));
		return restTemplate;
	}
}
