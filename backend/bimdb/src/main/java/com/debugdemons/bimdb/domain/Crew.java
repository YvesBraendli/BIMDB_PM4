package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Crew extends Credit {

    @JsonProperty("job")
    private String job;
}
