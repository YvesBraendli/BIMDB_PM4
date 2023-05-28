package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.People;
import com.debugdemons.bimdb.domain.SearchResultWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService extends BaseService {
	protected SearchService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
		super(movieDBApiConfig, restTemplate);
	}

	public SearchResultWrapper search(String query, int page) {
		String url = getSearchUrl("multi", query, page);
		return restTemplate.getForObject(url, SearchResultWrapper.class);
	}

	public DiscoverMovie searchMovies(String query, int page) {
		String url = getSearchUrl("movie", query, page);
		return restTemplate.getForObject(url, DiscoverMovie.class);
	}

	public DiscoverTv searchTvShows(String query, int page) {
		String url = getSearchUrl("tv", query, page);
		return restTemplate.getForObject(url, DiscoverTv.class);
	}

	public People searchPeople(String query, int page) {
		String url = getSearchUrl("person", query, page);
		return restTemplate.getForObject(url, People.class);
	}

	private String getSearchUrl(String route, String query, int page) {
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
				.withEndpoint("search/" + route)
				.withQuery(query)
				.withPage(page);
		return tmdbUrlBuilder.build();
	}
}
