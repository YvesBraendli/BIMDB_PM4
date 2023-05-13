package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media implements Serializable {
	@JsonProperty("backdropPath")
	@JsonAlias("backdrop_path")
	private String backdropPath;
	@JsonProperty("genreIds")
	@JsonAlias("genre_ids")
	private List<Integer> genreIds;
	@JsonProperty("id")
	private long apiId;
	@JsonProperty("originalLanguage")
	@JsonAlias("original_language")
	private String originalLanguage;
	@JsonProperty("overview")
	private String overview;
	@JsonProperty("popularity")
	private float popularity;
	@JsonProperty("posterPath")
	@JsonAlias({"poster_path", "profile_path"})
	private String posterPath;
	@JsonProperty("voteAverage")
	@JsonAlias("vote_average")
	private float voteAverage = -1f;
	@JsonProperty("voteCount")
	@JsonAlias("vote_count")
	private int voteCount;
	@JsonProperty("name")
	@JsonAlias("title")
	private String name;
	@JsonProperty("originalName")
	@JsonAlias({"original_name", "original_title"})
	private String originalName;

	@JsonProperty("releaseDate")
	@JsonAlias({"release_date", "first_air_date"})
	private Date releaseDate;
	@JsonProperty("mediaType")
	@JsonAlias("media_type")
	protected MediaType mediaType;

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	public long getApiId() {
		return apiId;
	}

	public void setApiId(long apiId) {
		this.apiId = apiId;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public float getPopularity() {
		return popularity;
	}

	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(float voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
}
