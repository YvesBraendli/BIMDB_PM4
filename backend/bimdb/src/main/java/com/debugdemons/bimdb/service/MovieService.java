package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.*;
import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.repository.FavoritesRepository;
import com.debugdemons.bimdb.repository.UsersRepository;
import com.debugdemons.bimdb.utils.Filter;
import com.debugdemons.bimdb.utils.FilterCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService extends BaseService {

	private final UsersRepository usersRepository;

	private final FavoritesRepository favoritesRepository;

	private final FilterCalculator<MovieDetails> filterCalculator;

	@Autowired
	public MovieService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate, UsersRepository usersRepository, FavoritesRepository favoritesRepository, FilterCalculator<MovieDetails> filterCalculator) {
		super(movieDBApiConfig, restTemplate);
		this.usersRepository = usersRepository;
		this.favoritesRepository = favoritesRepository;
		this.filterCalculator = filterCalculator;
	}

	public DiscoverMovie getMovies(Integer page, String username) {
		User user = usersRepository.findByUsername(username);
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
				.withEndpoint("discover/movie")
				.withPage(page);
		if (user != null) {
			if (user.getAdult() != null) {
				tmdbUrlBuilder.withIncludeAdult(user.getAdult());
			}
			if (StringUtils.hasText(user.getPreferredOriginalLanguage())) {
				tmdbUrlBuilder.withOriginalLanguage(user.getPreferredOriginalLanguage());
			}
			Set<Long> favoriteMovieIds = favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.MOVIE.getType());
			if (!CollectionUtils.isEmpty(favoriteMovieIds)) {
				Filter filter = filterCalculator.getFilter(getMovieDetails(favoriteMovieIds), favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.PERSON.getType()));
				applyFilter(user, tmdbUrlBuilder, filter);
			}
		}
		return restTemplate.getForObject(tmdbUrlBuilder.build(), DiscoverMovie.class);
	}

	public MovieDetails getMovieById(Long id) {
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
				.withEndpoint("movie/" + id)
				.withAppendToResponse(List.of("credits", "recommendations", "similar"));
		return restTemplate.getForObject(tmdbUrlBuilder.build(), MovieDetails.class);
	}

	public WatchProvidersResult getWatchProviders(Long id) {
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
				.withEndpoint("movie/" + id + "/watch/providers");
		return restTemplate.getForObject(tmdbUrlBuilder.build(), WatchProvidersResult.class);
	}

	private Set<MovieDetails> getMovieDetails(Set<Long> favoriteMovieIds) {
		Set<MovieDetails> movieDetails = new HashSet<>();
		if (!CollectionUtils.isEmpty(favoriteMovieIds)) {
			List<Long> sortedMovieIds = favoriteMovieIds.stream()
					.sorted()
					.collect(Collectors.toList());
			for (Long movieId : sortedMovieIds) {
				movieDetails.add(getMovieById(movieId));
			}
		}
		return movieDetails;
	}
}
