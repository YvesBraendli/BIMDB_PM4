package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TvShowSeasonDetails {

    @JsonProperty("id")
    private long apiId;

    @JsonProperty("air_date")
    private String airDate;

    @JsonProperty("episodes")
    private List<Episode> episodes;

    @JsonProperty("name")
    private String name;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("season_number")
    private Integer seasonNumber;
}
