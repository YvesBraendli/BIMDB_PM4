package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.People;
import com.debugdemons.bimdb.domain.SearchResultWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchServiceTest extends BaseServiceTest {
	@Autowired
	private SearchService searchService;

	@Test
	void search() throws JsonProcessingException {
		SearchResultWrapper searchResultWrapper = new SearchResultWrapper();
		searchResultWrapper.setTotalResults(40);
		searchResultWrapper.setTotalPages(2);
		searchResultWrapper.setPage(2);
		mockServerExpectGet("https://api.themoviedb.org/3/search/multi?query=test&page=1&language=en", searchResultWrapper);
		assertJsonEquals(searchResultWrapper, searchService.search("test", 1));
	}

	@Test
	void searchMovies() throws JsonProcessingException {
		DiscoverMovie discoverMovie = new DiscoverMovie();
		discoverMovie.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/search/movie?query=movie&page=5&language=en", discoverMovie);
		assertJsonEquals(discoverMovie, searchService.searchMovies("movie", 5));
	}

	@Test
	void searchTv() throws JsonProcessingException {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/search/tv?query=tv&page=2&language=en", discoverTv);
		assertJsonEquals(discoverTv, searchService.searchTvShows("tv", 2));
	}

	@Test
	void searchPeople() throws JsonProcessingException {
		People people = new People();
		people.setTotalPages(20);
		mockServerExpectGet("https://api.themoviedb.org/3/search/person?query=person&page=3&language=en", people);
		assertJsonEquals(people, searchService.searchPeople("person", 3));
	}

}
