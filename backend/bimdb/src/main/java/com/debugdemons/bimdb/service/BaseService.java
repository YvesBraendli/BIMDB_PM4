package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import org.springframework.web.client.RestTemplate;

public abstract class BaseService {

    protected final MovieDBApiConfig movieDBApiConfig;

    protected final RestTemplate restTemplate;

    protected BaseService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        this.movieDBApiConfig = movieDBApiConfig;
        this.restTemplate = restTemplate;
    }
}
