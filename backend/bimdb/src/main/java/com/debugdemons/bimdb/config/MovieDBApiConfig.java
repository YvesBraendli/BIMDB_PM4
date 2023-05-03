package com.debugdemons.bimdb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovieDBApiConfig {

    private String apiKey;
    private String baseUrl;

    public MovieDBApiConfig(@Value("${themoviedb.api.key}") String apiKey,
                            @Value("${themoviedb.api.base.url}") String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

}
