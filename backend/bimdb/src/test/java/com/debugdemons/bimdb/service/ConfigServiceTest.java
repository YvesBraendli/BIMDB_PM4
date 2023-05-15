package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.ApiConfig;
import com.debugdemons.bimdb.domain.ApiImagesConfig;
import com.debugdemons.bimdb.domain.Country;
import com.debugdemons.bimdb.domain.Language;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

class ConfigServiceTest extends BaseServiceTest {

	@Autowired
	private ConfigService configService;

	@Test
	void getApiConfig() throws JsonProcessingException {
		ApiImagesConfig expectedImagesConfig = new ApiImagesConfig();
		expectedImagesConfig.setBaseUrl("http://image.tmdb.org/t/p/");
		expectedImagesConfig.setSecureBaseUrl("https://image.tmdb.org/t/p/");
		expectedImagesConfig.setBackdropSizes(Arrays.asList("w300", "w780", "w1280"));
		expectedImagesConfig.setLogoSizes(Arrays.asList("w45", "w92", "w154", "w185", "w300", "w500"));
		expectedImagesConfig.setPosterSizes(Arrays.asList("w92", "w154", "w185", "w342", "w500", "w780"));
		expectedImagesConfig.setProfileSizes(Arrays.asList("w45", "w185", "h632"));
		expectedImagesConfig.setStillSizes(Arrays.asList("w92", "w185", "w300"));

		ApiConfig expectedApiConfig = new ApiConfig();
		expectedApiConfig.setImages(expectedImagesConfig);
		expectedApiConfig.setChangeKeys(Arrays.asList("title", "id"));

		mockServerExpectGet("https://api.themoviedb.org/3/configuration?language=en", expectedApiConfig);
		assertJsonEquals(expectedApiConfig, configService.getApiConfig());
	}

	@Test
	void getCountries() throws JsonProcessingException {
		Country switzerland = new Country();
		switzerland.setEnglishName("Switzerland");
		switzerland.setIso("CH");
		Country[] expectedCountries = new Country[]{switzerland};

		mockServerExpectGet("https://api.themoviedb.org/3/configuration/countries?language=en", expectedCountries);
		assertJsonEquals(expectedCountries, configService.getCountries());
	}

	@Test
	void getLanguages() throws JsonProcessingException {
		Language english = new Language();
		english.setName("Deutsch");
		english.setEnglishName("German");
		english.setIso("de");
		Language[] expectedLanguages = new Language[]{english};

		mockServerExpectGet("https://api.themoviedb.org/3/configuration/languages?language=en", expectedLanguages);
		assertJsonEquals(expectedLanguages, configService.getLanguages());
	}
}
