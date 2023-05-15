package com.debugdemons.bimdb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
public class BaseServiceTest {

	@Autowired
	protected RestTemplate restTemplate;
	protected MockRestServiceServer mockServer;
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@BeforeEach
	void init() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
		LocaleContextHolder.setLocale(Locale.ENGLISH);
	}

	@AfterEach
	void teardown() {
		mockServer.verify();
	}

	protected <T> void mockServerExpectGet(String url, T responseBody) throws JsonProcessingException {
		mockServerExpectGet(url, OBJECT_MAPPER.writeValueAsString(responseBody));
	}

	protected void mockServerExpectGet(String url, String responseBody) {
		mockServer.expect(ExpectedCount.once(),
						requestTo(url))
				.andExpect(method(HttpMethod.GET))
				.andExpect(header(HttpHeaders.AUTHORIZATION, "api_key"))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(responseBody));
	}

	protected static <T> void assertJsonEquals(T expected, T actual) throws JsonProcessingException {
		assertEquals(OBJECT_MAPPER.writeValueAsString(expected), OBJECT_MAPPER.writeValueAsString(actual));
	}

}
