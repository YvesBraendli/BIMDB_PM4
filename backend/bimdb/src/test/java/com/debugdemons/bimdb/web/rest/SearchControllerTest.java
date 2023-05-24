package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.People;
import com.debugdemons.bimdb.domain.SearchResultWrapper;
import com.debugdemons.bimdb.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SearchService searchService;

	@Test
	void search() throws Exception {
		SearchResultWrapper searchResultWrapper = new SearchResultWrapper();
		searchResultWrapper.setTotalResults(40);
		searchResultWrapper.setTotalPages(2);
		searchResultWrapper.setPage(2);
		when(searchService.search("test", 2)).thenReturn(searchResultWrapper);
		this.mockMvc.perform(get("/api/search?query=test&page=2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"page\":2,\"totalPages\":2,\"totalResults\":40,\"results\":null}")));
	}

	@Test
	void searchMovies() throws Exception {
		DiscoverMovie discoverMovie = new DiscoverMovie();
		discoverMovie.setTotalPages(30);
		discoverMovie.setPage(3);
		when(searchService.searchMovies("movie", 3)).thenReturn(discoverMovie);
		this.mockMvc.perform(get("/api/search/movies?query=movie&page=3")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"page\":3,\"totalPages\":30,\"totalResults\":0,\"results\":null}")));
	}

	@Test
	void searchTv() throws Exception {
		DiscoverTv discoverTv = new DiscoverTv();
		discoverTv.setTotalPages(10);
		discoverTv.setPage(2);
		when(searchService.searchTvShows("tv", 2)).thenReturn(discoverTv);
		this.mockMvc.perform(get("/api/search/tv?query=tv&page=2")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"page\":2,\"totalPages\":10,\"totalResults\":0,\"results\":null}")));
	}

	@Test
	void searchPeople() throws Exception {
		People people = new People();
		people.setTotalPages(20);
		people.setPage(5);
		when(searchService.searchPeople("person", 5)).thenReturn(people);
		this.mockMvc.perform(get("/api/search/people?query=person&page=5")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"page\":5,\"totalPages\":20,\"totalResults\":0,\"results\":null}")));
	}
}
