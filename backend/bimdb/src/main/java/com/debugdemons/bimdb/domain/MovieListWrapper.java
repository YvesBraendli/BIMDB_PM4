package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class MovieListWrapper implements Serializable {

    @JsonProperty("results")
    private List<Movie> results;
}
