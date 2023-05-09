package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class MovieDetails extends Movie implements Serializable {

    @JsonProperty("budget")
    private int budget;
    @JsonProperty("genres")
    private List<Genre> genres;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("revenue")
    private int revenue;
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
}
