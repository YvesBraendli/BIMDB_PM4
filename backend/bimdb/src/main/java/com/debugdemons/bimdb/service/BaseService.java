package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.utils.LoggerProvider;
import org.springframework.web.client.RestTemplate;

public abstract class BaseService implements LoggerProvider {

    protected final MovieDBApiConfig movieDBApiConfig;

    protected final RestTemplate restTemplate;

    protected BaseService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        this.movieDBApiConfig = movieDBApiConfig;
        this.restTemplate = restTemplate;
    }
}
