package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.domain.People;
import com.debugdemons.bimdb.domain.SearchResultWrapper;
import com.debugdemons.bimdb.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/search")
public class SearchController {

	private final SearchService searchService;

	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping()
	public SearchResultWrapper search(@RequestParam(name = "query") String query, @RequestParam(name = "page") int page) {
		return searchService.search(query, page);
	}

	@GetMapping("/movies")
	public DiscoverMovie searchMovies(@RequestParam(name = "query") String query, @RequestParam(name = "page") int page) {
		return searchService.searchMovies(query, page);
	}

	@GetMapping("/tv")
	public DiscoverTv searchTvShows(@RequestParam(name = "query") String query, @RequestParam(name = "page") int page) {
		return searchService.searchTvShows(query, page);
	}

	@GetMapping("/people")
	public People searchPeople(@RequestParam(name = "query") String query, @RequestParam(name = "page") int page) {
		return searchService.searchPeople(query, page);
	}
}
