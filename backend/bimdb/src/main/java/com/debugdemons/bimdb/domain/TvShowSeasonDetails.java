package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TvShowSeasonDetails {

	@JsonProperty("id")
	private long apiId;

	@JsonProperty("airDate")
	@JsonAlias("air_date")
	private String airDate;

	@JsonProperty("episodes")
	private List<Episode> episodes;

	@JsonProperty("name")
	private String name;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("posterPath")
	@JsonAlias("poster_path")
	private String posterPath;

	@JsonProperty("seasonNumber")
	@JsonAlias("season_number")
	private Integer seasonNumber;

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

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
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

	public Integer getSeasonNumber() {
		return seasonNumber;
	}

	public void setSeasonNumber(Integer seasonNumber) {
		this.seasonNumber = seasonNumber;
	}
}
