package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.People;
import com.debugdemons.bimdb.domain.SearchResultWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SearchServiceTest {
	@MockBean
	private RestTemplate restTemplate;
	@Autowired
	private SearchService searchService;

	@Test
	void search() {
		SearchResultWrapper searchResultWrapper = new SearchResultWrapper();
		searchResultWrapper.setTotalResults(40);
		searchResultWrapper.setTotalPages(2);
		searchResultWrapper.setPage(2);
		when(restTemplate.getForObject("https://api.themoviedb.org/3/search/multi?query=test&page=1&api_key=api_key", SearchResultWrapper.class)).thenReturn(searchResultWrapper);
		assertEquals(searchResultWrapper, searchService.search("test", 1));
	}

	@Test
	void searchMovies() {
		DiscoverMovie discoverMovie = new DiscoverMovie();
		discoverMovie.setTotalPages(20);
		when(restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?query=movie&page=5&api_key=api_key", DiscoverMovie.class)).thenReturn(discoverMovie);
		assertEquals(discoverMovie, searchService.searchMovies("movie", 5));
	}

	@Test
	void searchTv() {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(20);
		when(restTemplate.getForObject("https://api.themoviedb.org/3/search/tv?query=tv&page=2&api_key=api_key", DiscoverTv.class)).thenReturn(discoverTv);
		assertEquals(discoverTv, searchService.searchTvShows("tv", 2));
	}

	@Test
	void searchPeople() {
		People people = new People();
		people.setTotalPages(20);
		when(restTemplate.getForObject("https://api.themoviedb.org/3/search/person?query=person&page=3&api_key=api_key", People.class)).thenReturn(people);
		assertEquals(people, searchService.searchPeople("person", 3));
	}

}
