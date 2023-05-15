package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TvServiceTest extends BaseServiceTest {

	@Autowired
	private TvService tvService;

	@Test
	void discoverTv() throws JsonProcessingException {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/tv?language=en", discoverTv);
		assertJsonEquals(discoverTv, tvService.getTv(null));
	}

	@Test
	void discoverTvPage() throws JsonProcessingException {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/discover/tv?page=20&language=en", discoverTv);
		assertJsonEquals(discoverTv, tvService.getTv(20));
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
