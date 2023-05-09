package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.ApiConfig;
import com.debugdemons.bimdb.domain.Country;
import com.debugdemons.bimdb.domain.Language;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfigService extends BaseService {

    public ConfigService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        super(movieDBApiConfig, restTemplate);
    }

    public ApiConfig getApiConfig() {
        String url = movieDBApiConfig.getBaseUrl() + "configuration?api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, ApiConfig.class);
    }

    public Country[] getCountries() {
        String url = movieDBApiConfig.getBaseUrl() + "configuration/countries?api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, Country[].class);
    }

    public Language[] getLanguages() {
        String url = movieDBApiConfig.getBaseUrl() + "configuration/languages?api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, Language[].class);
    }
}
