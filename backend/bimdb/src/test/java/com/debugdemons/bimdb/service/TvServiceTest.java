package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.*;
import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.repository.FavoritesRepository;
import com.debugdemons.bimdb.repository.UsersRepository;
import com.debugdemons.bimdb.utils.Filter;
import com.debugdemons.bimdb.utils.FilterCalculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TvServiceTest extends BaseServiceTest {

	@MockBean
	private UsersRepository usersRepository;

	@MockBean
	private FavoritesRepository favoritesRepository;

	@MockBean
	private FilterCalculator<TvShowDetails> filterCalculator;

	@Autowired
	private TvService tvService;

	private static final String USERNAME = "testUser";

	@Test
	void discoverTv() throws JsonProcessingException {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/tv?language=en", discoverTv);
		assertJsonEquals(discoverTv, tvService.getTv(null, null));
	}

	@Test
	void discoverTvWithUser() throws JsonProcessingException {
		User user = new User();
		user.setUsername(USERNAME);
		user.setPreferredOriginalLanguage("en");
		user.setAdult(Boolean.FALSE);
		when(usersRepository.findByUsername(USERNAME)).thenReturn(user);

		when(favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.TV_SHOW.getType())).thenReturn(Collections.emptySet());

		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/tv?include_adult=false&with_original_language=en&language=en", discoverTv);
		assertJsonEquals(discoverTv, tvService.getTv(null, USERNAME));
	}

	@Test
	void discoverTvWithUserFavorites() throws JsonProcessingException {
		User user = new User();
		user.setUsername(USERNAME);
		user.setPreferredOriginalLanguage("en");
		user.setAdult(Boolean.FALSE);
		when(usersRepository.findByUsername(USERNAME)).thenReturn(user);

		when(favoritesRepository.findAllApiIdsByUserAndType(user, MediaType.TV_SHOW.getType())).thenReturn(Set.of(1L, 2L, 3L));
		mockServerExpectGet("https://api.themoviedb.org/3/tv/1?append_to_response=credits,recommendations,similar&language=en", new TvShowDetails());
		mockServerExpectGet("https://api.themoviedb.org/3/tv/2?append_to_response=credits,recommendations,similar&language=en", new TvShowDetails());
		mockServerExpectGet("https://api.themoviedb.org/3/tv/3?append_to_response=credits,recommendations,similar&language=en", new TvShowDetails());

		Filter filter = new Filter();
		filter.setGenresToInclude(List.of(10, 20, 30));
		filter.setMinVoteAverage(6.8f);
		filter.setLatestReleaseDate("2022-11-20");
		when(filterCalculator.getFilter(any())).thenReturn(filter);


		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/tv?include_adult=false&with_original_language=en&with_genres=10%7C20%7C30&language=en", discoverTv);
		assertJsonEquals(discoverTv, tvService.getTv(null, USERNAME));
	}

	@Test
	void discoverTvPage() throws JsonProcessingException {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/tv?page=20&language=en", discoverTv);
		assertJsonEquals(discoverTv, tvService.getTv(20, null));
	}

	@Test
	void tvShowDetails() throws JsonProcessingException {
		TvShowDetails tvShow = new TvShowDetails();
		tvShow.setApiId(305);
		tvShow.setName("Succession");
		mockServerExpectGet("https://api.themoviedb.org/3/tv/305?append_to_response=credits,recommendations,similar&language=en", tvShow);
		assertJsonEquals(tvShow, tvService.getTvShowById(305L));
	}

	@Test
	void getTvShowSeasonDetails() throws JsonProcessingException {
		TvShowSeasonDetails tvShowSeasonDetails = new TvShowSeasonDetails();
		tvShowSeasonDetails.setApiId(923);
		tvShowSeasonDetails.setName("Succession");
		tvShowSeasonDetails.setSeasonNumber(1);
		tvShowSeasonDetails.setOverview("Succession Season 1");
		tvShowSeasonDetails.setAirDate("2020-09-20");
		tvShowSeasonDetails.setEpisodes(new ArrayList<>());
		tvShowSeasonDetails.setPosterPath("");
		mockServerExpectGet("https://api.themoviedb.org/3/tv/305/season/1?language=en", tvShowSeasonDetails);
		assertJsonEquals(tvShowSeasonDetails, tvService.getTvShowSeasonDetails(305L, 1L));
	}

	@Test
	void watchProviders() throws JsonProcessingException {
		final String watchProviderJson = """
				{
					"id": 1399,
					"results": {
						"US": {
							"link": "https://www.themoviedb.org/tv/1399-game-of-thrones/watch?locale=US",
							"rent": [
								{
									"logo_path": "/peURlLlr8jggOwK53fJ5wdQl05y.jpg",
									"provider_id": 2,
									"provider_name": "Apple TV",
									"display_priority": 4
								}
							]
						}
					}
				}""";
		WatchProvider watchProvider = new WatchProvider();
		watchProvider.setProviderName("Apple TV");
		watchProvider.setLogoPath("/peURlLlr8jggOwK53fJ5wdQl05y.jpg");

		WatchProviders watchProviders = new WatchProviders();
		watchProviders.setCountry("US");
		watchProviders.getRent().add(watchProvider);

		WatchProvidersResult result = new WatchProvidersResult();
		result.setWatchProviders(List.of(watchProviders));

		mockServerExpectGet("https://api.themoviedb.org/3/tv/1399/watch/providers?language=en", watchProviderJson);
		assertJsonEquals(result, tvService.getWatchProviders(1399L));
	}
}
