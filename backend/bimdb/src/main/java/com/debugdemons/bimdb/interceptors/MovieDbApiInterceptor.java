package com.debugdemons.bimdb.interceptors;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * MovieDbApiInterceptor intercepts TMDB API calls, adds API bearer token to request header and language query param to request URI
 */
public class MovieDbApiInterceptor implements ClientHttpRequestInterceptor {

	private final MovieDBApiConfig movieDBApiConfig;

	public MovieDbApiInterceptor(MovieDBApiConfig movieDBApiConfig) {
		this.movieDBApiConfig = movieDBApiConfig;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		if (request.getURI().toString().startsWith(movieDBApiConfig.getBaseUrl())) {
			getLogger().info(String.format("API request to TMDB: %s", request.getURI()));
			request = new MovieDbHttpRequest(request, movieDBApiConfig.getApiKey());
		}
		return execution.execute(request, body);
	}


	private static class MovieDbHttpRequest extends HttpRequestWrapper {
		private static final String LANGUAGE_QUERY_PARAM = "language";
		private static final String TOKEN_TYPE = "Bearer ";

		public MovieDbHttpRequest(HttpRequest request, String apiKey) {
			super(request);
			request.getHeaders().add(HttpHeaders.AUTHORIZATION, TOKEN_TYPE + apiKey);
		}

		@Override
		public URI getURI() {
			final String decodedURI = URLDecoder.decode(getRequest().getURI().toString(), StandardCharsets.UTF_8);
			return UriComponentsBuilder.fromUriString(decodedURI).queryParam(LANGUAGE_QUERY_PARAM, LocaleContextHolder.getLocale())
					.build().toUri();
		}
	}
}
