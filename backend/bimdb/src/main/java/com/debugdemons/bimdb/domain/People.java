package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class People extends PaginationResponse {
	@JsonProperty("results")
	private List<Credit> results;

	public List<Credit> getResults() {
		return results;
	}

	public void setResults(List<Credit> results) {
		this.results = results;
	}
}
