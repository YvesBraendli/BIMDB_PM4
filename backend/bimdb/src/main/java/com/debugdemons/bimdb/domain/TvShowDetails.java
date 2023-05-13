package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TvShowDetails extends TvShow {
    @JsonProperty("episode_run_time")
    private List<Integer> episodeRunTime;
    @JsonProperty("genres")
    private List<Genre> genres;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("in_production")
    private boolean inProduction;
    @JsonProperty("languages")
    private List<String> languages;
    @JsonProperty("last_air_date")
    private String lastAirDate;
    @JsonProperty("networks")
    private List<Network> networks;
    @JsonProperty("number_of_episodes")
    private int numberOfEpisodes;
    @JsonProperty("number_of_seasons")
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
}
