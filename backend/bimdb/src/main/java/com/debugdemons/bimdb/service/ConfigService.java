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
		return restTemplate.getForObject(new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl()).withEndpoint("configuration").build(), ApiConfig.class);
	}

	public Country[] getCountries() {
		return restTemplate.getForObject(new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl()).withEndpoint("configuration/countries").build(), Country[].class);
	}

	public Language[] getLanguages() {
		return restTemplate.getForObject(new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl()).withEndpoint("configuration/languages").build(), Language[].class);
	}
}
