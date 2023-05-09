package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WatchProviders implements Serializable {

    @JsonProperty("country")
    private String country;

    @JsonProperty("flatrate")
    private List<WatchProvider> flatrate;

    @JsonProperty("rent")
    private List<WatchProvider> rent;

    @JsonProperty
    private List<WatchProvider> buy;

    public String getCountry() {
        return country;
    }

    public List<WatchProvider> getFlatrate() {
        if (flatrate == null) {
            flatrate = new ArrayList<>();
        }
        return flatrate;
    }

    public List<WatchProvider> getRent() {
        if (rent == null) {
            rent = new ArrayList<>();
        }
        return rent;
    }

    public List<WatchProvider> getBuy() {
        if (buy == null) {
            buy = new ArrayList<>();
        }
        return buy;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
