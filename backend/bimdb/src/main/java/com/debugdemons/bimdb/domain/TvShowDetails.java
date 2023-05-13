package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TvShowDetails extends TvShow {
	@JsonProperty("episodeRunTime")
	@JsonAlias("episode_run_time")
	private List<Integer> episodeRunTime;
	@JsonProperty("genres")
	private List<Genre> genres;
	@JsonProperty("homepage")
	private String homepage;
	@JsonProperty("inProduction")
	@JsonAlias("in_production")
	private boolean inProduction;
	@JsonProperty("languages")
	private List<String> languages;
	@JsonProperty("lastAirDate")
	@JsonAlias("last_air_date")
	private String lastAirDate;
	@JsonProperty("networks")
	private List<Network> networks;
	@JsonProperty("numberOfEpisodes")
	@JsonAlias("number_of_episodes")
	private int numberOfEpisodes;
	@JsonProperty("numberOfSeasons")
	@JsonAlias("number_of_seasons")
	private int numberOfSeasons;
	@JsonProperty("seasons")
	private List<Season> seasons;
	@JsonProperty("status")
	private String status;
	@JsonProperty("tagline")
	private String tagline;
	@JsonProperty("type")
	private String type;
	@JsonProperty("credits")
	private Credits credits;
	@JsonProperty("recommendations")
	private TvShowListWrapper recommendations;
	@JsonProperty("similar")
	private TvShowListWrapper similar;

	public TvShowDetails() {
		super();
	}

	public List<Integer> getEpisodeRunTime() {
		return episodeRunTime;
	}

	public void setEpisodeRunTime(List<Integer> episodeRunTime) {
		this.episodeRunTime = episodeRunTime;
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

	public boolean isInProduction() {
		return inProduction;
	}

	public void setInProduction(boolean inProduction) {
		this.inProduction = inProduction;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public String getLastAirDate() {
		return lastAirDate;
	}

	public void setLastAirDate(String lastAirDate) {
		this.lastAirDate = lastAirDate;
	}

	public List<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(List<Network> networks) {
		this.networks = networks;
	}

	public int getNumberOfEpisodes() {
		return numberOfEpisodes;
	}

	public void setNumberOfEpisodes(int numberOfEpisodes) {
		this.numberOfEpisodes = numberOfEpisodes;
	}

	public int getNumberOfSeasons() {
		return numberOfSeasons;
	}

	public void setNumberOfSeasons(int numberOfSeasons) {
		this.numberOfSeasons = numberOfSeasons;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Credits getCredits() {
		return credits;
	}

	public void setCredits(Credits credits) {
		this.credits = credits;
	}

	public TvShowListWrapper getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(TvShowListWrapper recommendations) {
		this.recommendations = recommendations;
	}

	public TvShowListWrapper getSimilar() {
		return similar;
	}

	public void setSimilar(TvShowListWrapper similar) {
		this.similar = similar;
	}
}
