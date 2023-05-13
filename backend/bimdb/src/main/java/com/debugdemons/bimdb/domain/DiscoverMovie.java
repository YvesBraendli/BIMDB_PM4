package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DiscoverMovie extends PaginationResponse {

	@JsonProperty("results")
	private List<Movie> results;

	public List<Movie> getResults() {
		return results;
	}

}
