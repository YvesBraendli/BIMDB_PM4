package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaginationResponse {
	@JsonProperty("page")
	private int page;
	@JsonProperty("totalPages")
	@JsonAlias("total_pages")
	private int totalPages;
	@JsonProperty("totalResults")
	@JsonAlias("total_results")
	private int totalResults;

	public int getPage() {
		return page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
}
