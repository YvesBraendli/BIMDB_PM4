package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class TvShowListWrapper implements Serializable {

    @JsonProperty("results")
    private List<TvShow> results;
}
