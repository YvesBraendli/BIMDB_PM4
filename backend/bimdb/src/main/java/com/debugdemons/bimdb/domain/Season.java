package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Season implements Serializable {
	@JsonProperty("id")
	private long apiId;
	@JsonProperty("airDate")
	@JsonAlias("air_date")
	private String airDate;
	@JsonProperty("episodeCount")
	@JsonAlias("episode_count")
	private int episodeCount;
	@JsonProperty("name")
	private String name;
	@JsonProperty("overview")
	private String overview;
	@JsonProperty("posterPath")
	@JsonAlias("poster_path")
	private String posterPath;
	@JsonProperty("seasonNumber")
	@JsonAlias("season_number")
	private int seasonNumber;

	public long getApiId() {
		return apiId;
	}

	public void setApiId(long apiId) {
		this.apiId = apiId;
	}

	public String getAirDate() {
		return airDate;
	}

	public void setAirDate(String airDate) {
		this.airDate = airDate;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}
}
