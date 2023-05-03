package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Season implements Serializable {
    @JsonProperty("id")
    private long apiId;
    @JsonProperty("air_date")
    private String airDate;
    @JsonProperty("episode_count")
    private int episodeCount;
    @JsonProperty("name")
    private String name;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("season_number")
    private int seasonNumber;
}
