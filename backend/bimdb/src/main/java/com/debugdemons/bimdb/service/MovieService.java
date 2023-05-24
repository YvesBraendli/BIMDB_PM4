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
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService extends BaseService {

	private final UsersRepository usersRepository;

	private final FavoritesRepository favoritesRepository;

	private final GenreService genreService;

	@Autowired
	public MovieService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate, UsersRepository usersRepository, FavoritesRepository favoritesRepository, GenreService genreService) {
		super(movieDBApiConfig, restTemplate);
		this.usersRepository = usersRepository;
		this.favoritesRepository = favoritesRepository;
		this.genreService = genreService;
	}

	public DiscoverMovie getMovies(Integer page, String username) {
		User user = usersRepository.findByUsername(username);
		List<Genre> allMovieGenres = genreService.getAllMovieGenres().getGenres();
		Filter filter = null;
		if (user != null) {
			Set<Long> favoriteMovieIds = favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.MOVIE.getType());
			if (!CollectionUtils.isEmpty(favoriteMovieIds)) {
				Set<MovieDetails> movieDetails = new HashSet<>();
				for (Long movieId : favoriteMovieIds) {
					movieDetails.add(getMovieById(movieId));
				}
				filter = new FilterCalculator().getFilter(movieDetails, favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.PERSON.getType()));
			}
		}
		TmdbUrlBuilder tmdbUrlBuilder = new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl(), "discover/movie")
				.withPageNumber(page);
		if (filter != null) {
			tmdbUrlBuilder.withGenres(filter.getGenresToInclude()).withCast(filter.getActors());
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
