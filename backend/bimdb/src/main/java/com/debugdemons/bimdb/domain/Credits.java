package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Credits implements Serializable {

    @JsonProperty("cast")
    private List<Cast> cast;

    @JsonProperty("crew")
    private List<Crew> crew;
}
