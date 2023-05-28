package com.debugdemons.bimdb.domain;

import com.debugdemons.bimdb.utils.Filterable;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieDetails extends Movie implements Filterable {
	@JsonProperty("budget")
	private Long budget;
	@JsonProperty("genres")
	private List<Genre> genres;
	@JsonProperty("homepage")
	private String homepage;
	@JsonProperty("imdbId")
	@JsonAlias("imdb_id")
	private String imdbId;
	@JsonProperty("revenue")
	private Long revenue;
	@JsonProperty("runtime")
	private Integer runtime;
	@JsonProperty("status")
	private String status;
	@JsonProperty("tagline")
	private String tagline;
	@JsonProperty("credits")
	private Credits credits;
	@JsonProperty("recommendations")
	private MovieListWrapper recommendations;
	@JsonProperty("similar")
	private MovieListWrapper similar;

	public MovieDetails() {
		super();
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public Credits getCredits() {
		return credits;
	}

	public void setCredits(Credits credits) {
		this.credits = credits;
	}

	public MovieListWrapper getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(MovieListWrapper recommendations) {
		this.recommendations = recommendations;
	}

	public MovieListWrapper getSimilar() {
		return similar;
	}

	public void setSimilar(MovieListWrapper similar) {
		this.similar = similar;
	}
}
