package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Episode {

    @JsonProperty("id")
    private long id;

    @JsonProperty("air_date")
    private Date airDate;

    @JsonProperty("episode_number")
    private int episodeNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("production_code")
    private String productionCode;

    @JsonProperty("runtime")
    private int runtime;

    @JsonProperty("season_number")
    private int seasonNumber;

    @JsonProperty("show_id")
    private long showId;

    @JsonProperty("still_path")
    private String stillPath;

    @JsonProperty("vote_average")
    private float voteAverage;

    @JsonProperty("vote_count")
    private int voteCount;
}
