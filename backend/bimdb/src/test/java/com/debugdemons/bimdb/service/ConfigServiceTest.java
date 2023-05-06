package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.ApiConfig;
import com.debugdemons.bimdb.domain.ApiImagesConfig;
import com.debugdemons.bimdb.domain.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConfigServiceTest {

    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private ConfigService configService;

    @Test
    void getApiConfig() {
        ApiImagesConfig apiImagesConfig = new ApiImagesConfig();
        apiImagesConfig.setBaseUrl("http://image.tmdb.org/t/p/");
        apiImagesConfig.setSecureBaseUrl("https://image.tmdb.org/t/p/");
        apiImagesConfig.setBackdropSizes(Arrays.asList("w300", "w780", "w1280"));
        apiImagesConfig.setLogoSizes(Arrays.asList("w45", "w92", "w154", "w185", "w300", "w500"));
        apiImagesConfig.setPosterSizes(Arrays.asList("w92", "w154", "w185", "w342", "w500", "w780"));
        apiImagesConfig.setProfileSizes(Arrays.asList("w45", "w185", "h632"));
        apiImagesConfig.setStillSizes(Arrays.asList("w92", "w185", "w300"));
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setImages(apiImagesConfig);
        apiConfig.setChangeKeys(Arrays.asList("title", "id"));
        when(restTemplate.getForObject("https://api.themoviedb.org/3/configuration?api_key=api_key", ApiConfig.class)).thenReturn(apiConfig);
        assertEquals(apiConfig, configService.getApiConfig());
    }

    @Test
    void getCountries() {
        Country switzerland = new Country();
        switzerland.setEnglishName("Switzerland");
        switzerland.setIso("CH");
        Country[] countries = new Country[]{switzerland};
        when(restTemplate.getForObject("https://api.themoviedb.org/3/configuration/countries?api_key=api_key", Country[].class)).thenReturn(countries);
        assertEquals(countries, configService.getCountries());
    }
}
