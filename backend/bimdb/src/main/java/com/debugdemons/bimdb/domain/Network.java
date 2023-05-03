package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Network implements Serializable {

    @JsonProperty("id")
    private long apiId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("origin_country")
    private String originCountry;
    @JsonProperty("logo_path")
    private String logoPath;
}
