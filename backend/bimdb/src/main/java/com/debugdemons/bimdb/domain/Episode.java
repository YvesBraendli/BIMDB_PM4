package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Episode {

	@JsonProperty("id")
	private long id;

	@JsonProperty("airDate")
	@JsonAlias("air_date")
	private Date airDate;

	@JsonProperty("episodeNumber")
	@JsonAlias("episode_number")
	private int episodeNumber;

	@JsonProperty("name")
	private String name;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("productionCode")
	@JsonAlias("production_code")
	private String productionCode;

	@JsonProperty("runtime")
	private int runtime;

	@JsonProperty("seasonNumber")
	@JsonAlias("season_number")
	private int seasonNumber;

	@JsonProperty("showId")
	@JsonAlias("show_id")
	private long showId;

	@JsonProperty("stillPath")
	@JsonAlias("still_path")
	private String stillPath;

	@JsonProperty("voteAverage")
	@JsonAlias("vote_average")
	private float voteAverage;

	@JsonProperty("voteCount")
	@JsonAlias("vote_count")
	private int voteCount;

	public long getId() {
		return id;
	}

	public Date getAirDate() {
		return airDate;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public String getName() {
		return name;
	}

	public String getOverview() {
		return overview;
	}

	public String getProductionCode() {
		return productionCode;
	}

	public int getRuntime() {
		return runtime;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public long getShowId() {
		return showId;
	}

	public String getStillPath() {
		return stillPath;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}
}
