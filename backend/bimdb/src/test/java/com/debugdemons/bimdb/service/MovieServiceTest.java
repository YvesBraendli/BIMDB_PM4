package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MovieServiceTest extends BaseServiceTest {

	@Autowired
	private MovieService movieService;

	@Test
	void discoverMovie() throws JsonProcessingException {
		DiscoverMovie expectedDiscoverMovie = new DiscoverMovie();
		expectedDiscoverMovie.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/movie?language=en", expectedDiscoverMovie);
		DiscoverMovie discoverMovie = movieService.getMovies(null);
		assertJsonEquals(expectedDiscoverMovie, discoverMovie);
	}

	@Test
	void discoverMoviePage() throws JsonProcessingException {
		DiscoverMovie expectedDiscoverMovie = new DiscoverMovie();
		expectedDiscoverMovie.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/movie?page=15&language=en", expectedDiscoverMovie);
		assertJsonEquals(expectedDiscoverMovie, movieService.getMovies(15));
	}

	@Test
	void movieDetails() throws JsonProcessingException {
		MovieDetails expectedMovie = new MovieDetails();
		expectedMovie.setApiId(538);
		expectedMovie.setName("Interstellar");
		mockServerExpectGet("https://api.themoviedb.org/3/movie/538?append_to_response=credits,recommendations,similar&language=en", expectedMovie);
		assertJsonEquals(expectedMovie, movieService.getMovieById(538L));
	}

	@Test
	void watchProviders() throws JsonProcessingException {
		final String watchProviderJson = """
				{
					"id": 594767,
					"results": {
						"CH": {
							"link": "https://www.themoviedb.org/movie/550-fight-club/watch?locale=CH",
							"flatrate": [
								{
									"display_priority": 1,
									"logo_path": "/68MNrwlkpF7WnmNPXLah69CR5cb.jpg",
									"provider_id": 119,
									"provider_name": "Amazon Prime Video"
								}
							]
						}
					}
				}""";
		WatchProvider watchProvider = new WatchProvider();
		watchProvider.setProviderName("Amazon Prime Video");
		watchProvider.setLogoPath("/68MNrwlkpF7WnmNPXLah69CR5cb.jpg");

		WatchProviders watchProviders = new WatchProviders();
		watchProviders.setCountry("CH");
		watchProviders.getFlatrate().add(watchProvider);

		WatchProvidersResult result = new WatchProvidersResult();
		result.setWatchProviders(List.of(watchProviders));

		mockServerExpectGet("https://api.themoviedb.org/3/movie/538/watch/providers?language=en", watchProviderJson);
		assertJsonEquals(result, movieService.getWatchProviders(538L));
	}
}
