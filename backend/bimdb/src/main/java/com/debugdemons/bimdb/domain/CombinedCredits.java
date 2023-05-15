package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class CombinedCredits implements Serializable {

    @JsonProperty("cast")
    private List<Media> cast;

    @JsonProperty("crew")
    private List<Media> crew;
}
