package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Movie extends Media {

    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("original_title")
    private String name;
    @JsonProperty("release_date")
    private Date releaseDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("video")
    private boolean video;

    public boolean isAdult() {
        return adult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

}
