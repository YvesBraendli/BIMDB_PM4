package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService extends BaseService {

	public MovieService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
		super(movieDBApiConfig, restTemplate);
	}


	public DiscoverMovie getMovies(Integer page) {
		String url = movieDBApiConfig.getBaseUrl() + "discover/movie";
		if (page != null) {
			url += "?page=" + page;
		}
		return restTemplate.getForObject(url, DiscoverMovie.class);
	}

	public MovieDetails getMovieById(Long id) {
		String url = movieDBApiConfig.getBaseUrl() + "movie/" + id + "?append_to_response=credits,recommendations,similar";
		return restTemplate.getForObject(url, MovieDetails.class);
	}

	public WatchProvidersResult getWatchProviders(Long id) {
		String url = movieDBApiConfig.getBaseUrl() + "movie/" + id + "/watch/providers";
		return restTemplate.getForObject(url, WatchProvidersResult.class);
	}
}
