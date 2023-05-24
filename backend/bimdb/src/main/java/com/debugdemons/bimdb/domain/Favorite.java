package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Favorite implements Serializable {

    @JsonProperty("mediaType")
    @JsonAlias("media_type")
    private MediaType mediaType;

    @JsonProperty("id")
    private Long id;

    public Favorite(MediaType mediaType, Long id) {
        this.mediaType = mediaType;
        this.id = id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public Long getId() {
        return id;
    }

}
