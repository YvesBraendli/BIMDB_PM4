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

@Service
public class MovieService extends BaseService {

	private final UsersRepository usersRepository;

	private final FavoritesRepository favoritesRepository;

	@Autowired
	public MovieService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate, UsersRepository usersRepository, FavoritesRepository favoritesRepository) {
		super(movieDBApiConfig, restTemplate);
		this.usersRepository = usersRepository;
		this.favoritesRepository = favoritesRepository;
	}

	public DiscoverMovie getMovies(Integer page, String username) {
		User user = usersRepository.findByUsername(username);
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl())
				.withEndpoint("discover/movie")
				.withPage(page);
		Filter filter = null;
		if (user != null) {
			if (user.getAdult() != null) {
				tmdbUrlBuilder.withIncludeAdult(user.getAdult());
			}
			if (StringUtils.hasText(user.getPreferredOriginalLanguage())) {
				tmdbUrlBuilder.withOriginalLanguage(user.getPreferredOriginalLanguage());
			}
			Set<Long> favoriteMovieIds = favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.MOVIE.getType());
			filter = new FilterCalculator<MovieDetails>().getFilter(getMovieDetails(favoriteMovieIds), favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.PERSON.getType()));
		}

		if (filter != null) {
			tmdbUrlBuilder.withGenres(filter.getGenresToInclude())
					.withCast(filter.getActors());
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
			for (Long movieId : favoriteMovieIds) {
				movieDetails.add(getMovieById(movieId));
			}
		}
		return movieDetails;
	}
}
