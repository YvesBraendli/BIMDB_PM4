package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResultWrapper extends PaginationResponse {
	@JsonProperty("results")
	private List<Media> results;

	public List<Media> getResults() {
		return results;
	}

	public void setResults(List<Media> results) {
		this.results = results;
	}
}
