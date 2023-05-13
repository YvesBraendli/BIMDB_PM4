package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.ApiConfig;
import com.debugdemons.bimdb.domain.ApiImagesConfig;
import com.debugdemons.bimdb.domain.Country;
import com.debugdemons.bimdb.domain.Language;
import com.debugdemons.bimdb.service.ConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ConfigControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConfigService configService;


	@Test
	void getApiConfig() throws Exception {
		ApiConfig apiConfig = new ApiConfig();
		apiConfig.setImages(new ApiImagesConfig());
		apiConfig.setChangeKeys(Arrays.asList("title", "id"));
		when(configService.getApiConfig()).thenReturn(apiConfig);
		this.mockMvc.perform(get("/api/config")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"images\":{\"baseUrl\":null,\"secureBaseUrl\":null,\"backdropSizes\":null,\"logoSizes\":null,\"posterSizes\":null,\"profileSizes\":null,\"stillSizes\":null},\"changeKeys\":[\"title\",\"id\"]}")));
	}

	@Test
	void getCountries() throws Exception {
		Country switzerland = new Country();
		switzerland.setEnglishName("Switzerland");
		switzerland.setIso("CH");
		Country[] countries = new Country[]{switzerland};
		when(configService.getCountries()).thenReturn(countries);
		this.mockMvc.perform(get("/api/config/countries")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"iso\":\"CH\",\"englishName\":\"Switzerland\"}]")));
	}

	@Test
	void getLanguages() throws Exception {
		Language english = new Language();
		english.setName("English");
		english.setEnglishName("English");
		english.setIso("en");
		Language[] languages = new Language[]{english};
		when(configService.getLanguages()).thenReturn(languages);
		this.mockMvc.perform(get("/api/config/languages")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"iso\":\"en\",\"englishName\":\"English\",\"name\":\"English\"}]")));
	}
}