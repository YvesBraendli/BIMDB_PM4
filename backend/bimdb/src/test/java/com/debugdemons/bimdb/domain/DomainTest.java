package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Test
	void mapApiConfig() throws JsonProcessingException {
		ApiConfig apiConfig = OBJECT_MAPPER.readValue("""
				{
				  "images": {
				    "base_url": "http://image.tmdb.org/t/p/",
				    "secure_base_url": "https://image.tmdb.org/t/p/",
				    "backdrop_sizes": [
				      "w300"
				    ],
				    "logo_sizes": [
				      "w45"
				    ],
				    "poster_sizes": [
				      "w92"
				    ],
				    "profile_sizes": [
				      "h632"
				    ],
				    "still_sizes": [
				      "original"
				    ]
				  },
				  "change_keys": [
				    "adult",
				    "air_date"
				  ]
				}""", ApiConfig.class);

		assertNotNull(apiConfig);
		assertNotNull(apiConfig.getImages());
		assertNotNull(apiConfig.getChangeKeys());
		ApiImagesConfig imagesConfig = apiConfig.getImages();
		assertEquals("http://image.tmdb.org/t/p/", imagesConfig.getBaseUrl());
		assertEquals("https://image.tmdb.org/t/p/", imagesConfig.getSecureBaseUrl());
		assertNotNull(imagesConfig.getBackdropSizes());
		assertEquals(1, imagesConfig.getBackdropSizes().size());
		assertEquals("w300", imagesConfig.getBackdropSizes().get(0));
		assertNotNull(imagesConfig.getLogoSizes());
		assertEquals(1, imagesConfig.getLogoSizes().size());
		assertEquals("w45", imagesConfig.getLogoSizes().get(0));
		assertNotNull(imagesConfig.getPosterSizes());
		assertEquals(1, imagesConfig.getPosterSizes().size());
		assertEquals("w92", imagesConfig.getPosterSizes().get(0));
		assertNotNull(imagesConfig.getProfileSizes());
		assertEquals(1, imagesConfig.getProfileSizes().size());
		assertEquals("h632", imagesConfig.getProfileSizes().get(0));
		assertNotNull(imagesConfig.getStillSizes());
		assertEquals(1, imagesConfig.getStillSizes().size());
		assertEquals("original", imagesConfig.getStillSizes().get(0));
	}

	@Test
	void mapCountry() throws JsonProcessingException {
		Country country = OBJECT_MAPPER.readValue("""
				{
				  "iso_3166_1": "CH",
				  "english_name": "Switzerland"
				}""", Country.class);

		assertNotNull(country);
		assertEquals("CH", country.getIso());
		assertEquals("Switzerland", country.getEnglishName());
	}

	@Test
	void mapCast() throws JsonProcessingException {
		Cast cast = OBJECT_MAPPER.readValue("""
				{
				  "character": "The Narrator"
				}""", Cast.class);

		assertNotNull(cast);
		assertEquals("The Narrator", cast.getCharacter());
	}

	@Test
	void mapCredit() throws JsonProcessingException {
		Credit credit = OBJECT_MAPPER.readValue("""
				{
				  "id": 819,
				  "name": "Edward Norton",
				  "profile_path": "/5XBzD5WuTyVQZeS4VI25z2moMeY.jpg"
				}""", Credit.class);

		assertNotNull(credit);
		assertEquals(819, credit.getId());
		assertEquals("Edward Norton", credit.getName());
		assertEquals("/5XBzD5WuTyVQZeS4VI25z2moMeY.jpg", credit.getProfilePath());
	}

	@Test
	void mapCrew() throws JsonProcessingException {
		Crew crew = OBJECT_MAPPER.readValue("""
				{
				  "job": "Producer"
				}""", Crew.class);

		assertNotNull(crew);
		assertEquals("Producer", crew.getJob());
	}

	@Test
	void mapDiscover() throws JsonProcessingException {
		PaginationResponse paginationResponse = OBJECT_MAPPER.readValue("""
				{
				  "page": 1,
				  "total_results": 500,
				  "total_pages": 25
				}""", PaginationResponse.class);

		assertNotNull(paginationResponse);
		assertEquals(1, paginationResponse.getPage());
		assertEquals(25, paginationResponse.getTotalPages());
		assertEquals(500, paginationResponse.getTotalResults());
	}

	@Test
	void mapEpisode() throws JsonProcessingException {
		Episode episode = OBJECT_MAPPER.readValue("""
				{
				  "air_date": "2011-04-17",
				  "episode_number": 1,
				  "name": "Winter Is Coming",
				  "overview": "Jon Arryn,...",
				  "id": 63056,
				  "production_code": "101",
				  "runtime": 62,
				  "season_number": 1,
				  "show_id": 1399,
				  "still_path": "/wrGWeW4WKxnaeA8sxJb2T9O6ryo.jpg",
				  "vote_average": 7.11904761904762,
				  "vote_count": 21
				}""", Episode.class);

		assertNotNull(episode);
		assertNotNull(episode.getAirDate());
		assertEquals(63056, episode.getId());
		assertEquals(1399, episode.getShowId());
		assertEquals(62, episode.getRuntime());
		assertEquals(1, episode.getEpisodeNumber());
		assertEquals("Winter Is Coming", episode.getName());
		assertEquals("Jon Arryn,...", episode.getOverview());
		assertEquals("101", episode.getProductionCode());
		assertEquals(1, episode.getSeasonNumber());
		assertEquals("/wrGWeW4WKxnaeA8sxJb2T9O6ryo.jpg", episode.getStillPath());
		assertEquals(7.11904761904762f, episode.getVoteAverage());
		assertEquals(21, episode.getVoteCount());
	}

	@Test
	void mapLanguage() throws JsonProcessingException {
		Language language = OBJECT_MAPPER.readValue("""
				{
				  "iso_639_1": "de",
				  "english_name": "German",
				  "name": "Deutsch"
				}""", Language.class);

		assertNotNull(language);
		assertEquals("de", language.getIso());
		assertEquals("Deutsch", language.getName());
		assertEquals("German", language.getEnglishName());
	}

	@Test
	void mapMovie() throws JsonProcessingException {
		Movie movie = OBJECT_MAPPER.readValue("""
				{
				  "adult": false,
				  "backdrop_path": "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
				  "genre_ids": [
				    18
				  ],
				  "id": 550,
				  "original_language": "en",
				  "original_title": "Fight Club",
				  "overview": "A ticking-time-bomb ...",
				  "popularity": 0.5,
				  "poster_path": "/posterfCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
				  "release_date": "1999-10-12",
				  "title": "Fight Club",
				  "video": false,
				  "vote_average": 7.8,
				  "vote_count": 3439
				}""", Movie.class);

		assertNotNull(movie);
		assertFalse(movie.isAdult());
		assertEquals("/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg", movie.getBackdropPath());
		assertNotNull(movie.getGenreIds());
		assertEquals(1, movie.getGenreIds().size());
		assertEquals(18, movie.getGenreIds().get(0));
		assertEquals(550, movie.getApiId());
		assertEquals("en", movie.getOriginalLanguage());
		assertEquals("Fight Club", movie.getOriginalName());
		assertEquals("A ticking-time-bomb ...", movie.getOverview());
		assertEquals(0.5f, movie.getPopularity());
		assertEquals("/posterfCayJrkfRaCRCTh8GqN30f8oyQF.jpg", movie.getPosterPath());
		assertNotNull(movie.getReleaseDate());
		assertEquals("Fight Club", movie.getName());
		assertFalse(movie.isVideo());
		assertEquals(7.8f, movie.getVoteAverage());
		assertEquals(3439, movie.getVoteCount());
	}

	@Test
	void mapMovieDetails() throws JsonProcessingException {
		MovieDetails movieDetails = OBJECT_MAPPER.readValue("""
				{
				  "budget": 63000000,
				  "genres": [
				    {
				      "id": 18,
				      "name": "Drama"
				    }
				  ],
				  "homepage": "home",
				  "imdb_id": "tt0137523",
				  "revenue": 100853753,
				  "runtime": 139,
				  "status": "Released",
				  "tagline": "How much can you know about yourself if you've never been in a fight?",
				  "recommendations": {
				   "results": []
				  },
				  "similar": {
				   "results": []
				  },
				  "credits": {
				   "cast": [],
				   "crew": []
				  }
				}""", MovieDetails.class);

		assertNotNull(movieDetails);
		assertEquals(63000000, movieDetails.getBudget());
		assertNotNull(movieDetails.getGenres());
		assertEquals(1, movieDetails.getGenres().size());
		assertEquals("home", movieDetails.getHomepage());
		assertEquals("tt0137523", movieDetails.getImdbId());
		assertEquals(100853753, movieDetails.getRevenue());
		assertEquals(139, movieDetails.getRuntime());
		assertEquals("Released", movieDetails.getStatus());
		assertEquals("How much can you know about yourself if you've never been in a fight?", movieDetails.getTagline());
		assertNotNull(movieDetails.getRecommendations());
		assertNotNull(movieDetails.getSimilar());
		assertNotNull(movieDetails.getCredits());
		assertNotNull(movieDetails.getCredits().getCast());
		assertNotNull(movieDetails.getCredits().getCrew());
	}

	@Test
	void mapNetwork() throws JsonProcessingException {
		Network network = OBJECT_MAPPER.readValue("""
				{
				  "id": 213,
				  "logo_path": "/wwemzKWzjKYJFfCeiB57q3r4Bcm.png",
				  "name": "Netflix",
				  "origin_country": "USA"
				}""", Network.class);

		assertNotNull(network);
		assertEquals(213, network.getApiId());
		assertEquals("/wwemzKWzjKYJFfCeiB57q3r4Bcm.png", network.getLogoPath());
		assertEquals("Netflix", network.getName());
		assertEquals("USA", network.getOriginCountry());
	}

	@Test
	void mapPeople() throws JsonProcessingException {
		People people = OBJECT_MAPPER.readValue("""
				{
				  "results": []
				}""", People.class);

		assertNotNull(people);
		assertNotNull(people.getResults());
	}

	@Test
	void mapSearchResult() throws JsonProcessingException {
		SearchResultWrapper searchResultWrapper = OBJECT_MAPPER.readValue("""
				{
				  "results": [
				    {
				      "profile_path": "/profile.jpg",
				      "media_type": "person"
				    }
				  ]
				}""", SearchResultWrapper.class);

		assertNotNull(searchResultWrapper);
		assertNotNull(searchResultWrapper.getResults());
		assertEquals(1, searchResultWrapper.getResults().size());
		Media media = searchResultWrapper.getResults().get(0);
		assertNotNull(media);
		assertEquals("/profile.jpg", media.getPosterPath());
		assertEquals(MediaType.PERSON, media.getMediaType());
	}

	@Test
	void mapSeason() throws JsonProcessingException {
		Season season = OBJECT_MAPPER.readValue("""
				{
				  "air_date": "2011-04-17",
				  "name": "Season 1",
				  "episode_count": 10,
				  "overview": "Overview",
				  "id": 3624,
				  "poster_path": "/zwaj4egrhnXOBIit1tyb4Sbt3KP.jpg",
				  "season_number": 1
				}""", Season.class);

		assertNotNull(season);
		assertNotNull(season.getAirDate());
		assertEquals(1, season.getSeasonNumber());
		assertEquals(3624, season.getApiId());
		assertEquals("/zwaj4egrhnXOBIit1tyb4Sbt3KP.jpg", season.getPosterPath());
		assertEquals("Overview", season.getOverview());
		assertEquals("Season 1", season.getName());
		assertEquals(10, season.getEpisodeCount());
	}

	@Test
	void mapTvShow() throws JsonProcessingException {
		TvShow tvShow = OBJECT_MAPPER.readValue("""
				{
				  "origin_country": [
				   "US"
				  ]
				}""", TvShow.class);

		assertNotNull(tvShow);
		assertNotNull(tvShow.getOriginCountry());
		assertEquals(1, tvShow.getOriginCountry().size());
		assertEquals("US", tvShow.getOriginCountry().get(0));
	}

	@Test
	void mapTvShowDetails() throws JsonProcessingException {
		TvShowDetails tvShowDetails = OBJECT_MAPPER.readValue("""
				{
				  "episode_run_time": [],
				  "genres": [],
				  "homepage": "http://www.hbo.com/game-of-thrones",
				  "in_production": false,
				  "languages": [],
				  "networks": [],
				  "number_of_episodes": 73,
				  "number_of_seasons": 8,
				  "seasons": [],
				  "status": "Ended",
				  "tagline": "Winter Is Coming",
				  "last_air_date": "2019-05-19",
				  "type": "Scripted",
				  "recommendations": {
				   "results": []
				  },
				  "similar": {
				   "results": []
				  },
				  "credits": {
				   "cast": [],
				   "crew": []
				  }
				}""", TvShowDetails.class);

		assertNotNull(tvShowDetails);
		assertNotNull(tvShowDetails.getLastAirDate());
		assertNotNull(tvShowDetails.getEpisodeRunTime());
		assertNotNull(tvShowDetails.getSeasons());
		assertNotNull(tvShowDetails.getGenres());
		assertNotNull(tvShowDetails.getLanguages());
		assertNotNull(tvShowDetails.getNetworks());
		assertNotNull(tvShowDetails.getRecommendations());
		assertNotNull(tvShowDetails.getSimilar());
		assertNotNull(tvShowDetails.getCredits());
		assertFalse(tvShowDetails.isInProduction());
		assertEquals("http://www.hbo.com/game-of-thrones", tvShowDetails.getHomepage());
		assertEquals("Ended", tvShowDetails.getStatus());
		assertEquals("Winter Is Coming", tvShowDetails.getTagline());
		assertEquals("Scripted", tvShowDetails.getType());
		assertEquals(73, tvShowDetails.getNumberOfEpisodes());
		assertEquals(8, tvShowDetails.getNumberOfSeasons());
	}

	@Test
	void mapTvShowSeasonDetails() throws JsonProcessingException {
		TvShowSeasonDetails tvShowSeasonDetails = OBJECT_MAPPER.readValue("""
				{
				   "air_date": "2011-04-17",
				   "episodes": [],
				   "name": "Season 1",
				   "overview": "Overview",
				   "id": 3624,
				   "poster_path": "/zwaj4egrhnXOBIit1tyb4Sbt3KP.jpg",
				   "season_number": 1
				 }""", TvShowSeasonDetails.class);

		assertNotNull(tvShowSeasonDetails);
		assertNotNull(tvShowSeasonDetails.getEpisodes());
		assertEquals("2011-04-17", tvShowSeasonDetails.getAirDate());
		assertEquals("Season 1", tvShowSeasonDetails.getName());
		assertEquals("Overview", tvShowSeasonDetails.getOverview());
		assertEquals("/zwaj4egrhnXOBIit1tyb4Sbt3KP.jpg", tvShowSeasonDetails.getPosterPath());
		assertEquals(3624, tvShowSeasonDetails.getApiId());
		assertEquals(1, tvShowSeasonDetails.getSeasonNumber());
	}

	@Test
	void mapWatchProvider() throws JsonProcessingException {
		WatchProvider watchProvider = OBJECT_MAPPER.readValue("""
				{
				      "logo_path": "/9A1JSVmSxsyaBK4SUFsYVqbAYfW.jpg",
				      "provider_name": "Netflix"
				    }""", WatchProvider.class);

		assertNotNull(watchProvider);
		assertEquals("Netflix", watchProvider.getProviderName());
		assertEquals("/9A1JSVmSxsyaBK4SUFsYVqbAYfW.jpg", watchProvider.getLogoPath());
	}
}
