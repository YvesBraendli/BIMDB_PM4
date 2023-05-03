package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TvShow extends Media {

    @JsonProperty("first_air_date")
    private String firstAirDate;
    @JsonProperty("origin_country")
    private List<String> originCountry;
    @JsonProperty("name")
    private String name;
    @JsonProperty("original_name")
    private String originalName;

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

}
