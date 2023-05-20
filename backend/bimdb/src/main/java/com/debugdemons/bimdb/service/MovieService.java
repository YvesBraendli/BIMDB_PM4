package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.debugdemons.bimdb.model.UserPreferences;
import com.debugdemons.bimdb.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService extends BaseService {

	@Autowired
	private PreferencesRepository preferencesRepository;

	public MovieService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
		super(movieDBApiConfig, restTemplate);
	}

	public DiscoverMovie getMovies(Integer page, String username) {
		UserPreferences userPreferences = preferencesRepository.findByUsernameAndSource(username, "TMDB");
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl(), "discover/movie")
				.withPageNumber(page)
				.withGenres(userPreferences.getFavoriteMovieGenres())
				.withCast(userPreferences.getFavoriteActors());
		String url = tmdbUrlBuilder.build();
		getLogger().info("API request to TMDB: " + url);
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
