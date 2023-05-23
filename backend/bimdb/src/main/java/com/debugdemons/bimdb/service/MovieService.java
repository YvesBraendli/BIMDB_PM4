package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.debugdemons.bimdb.model.UserPreferences;
import com.debugdemons.bimdb.repository.PreferencesRepository;
import com.debugdemons.bimdb.utils.Filter;
import com.debugdemons.bimdb.utils.FilterCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class MovieService extends BaseService {

	private final PreferencesRepository preferencesRepository;

	@Autowired
	public MovieService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate, PreferencesRepository preferencesRepository) {
		super(movieDBApiConfig, restTemplate);
		this.preferencesRepository = preferencesRepository;
	}

	public DiscoverMovie getMovies(Integer page, String username) {
		UserPreferences userPreferences = preferencesRepository.findByUsername(username);
		Filter filter = null;
		if (userPreferences != null) {
			Set<Long> favoriteMovieIds = userPreferences.getFavoriteMovies();
			if (!CollectionUtils.isEmpty(favoriteMovieIds)) {
				Set<MovieDetails> movieDetails = new HashSet<>();
				for (Long movieId : favoriteMovieIds) {
					movieDetails.add(getMovieById(movieId));
				}
				filter = FilterCalculator.calculateMovieFilter(movieDetails);
			}
		}
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl(), "discover/movie")
				.withPageNumber(page);
		if (filter != null) {
			tmdbUrlBuilder.withFavoriteMovieGenres(filter.getGenresToInclude());
					//TODO: add rest of filtering attributes.withCast(userPreferences.getFavoriteActors());
		}
		return restTemplate.getForObject(tmdbUrlBuilder.build(), DiscoverMovie.class);
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
