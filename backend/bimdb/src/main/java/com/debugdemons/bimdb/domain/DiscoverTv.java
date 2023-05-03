package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DiscoverTv extends Discover {
    @JsonProperty("results")
    private List<TvShow> results;

    public List<TvShow> getResults() {
        return results;
    }
}
