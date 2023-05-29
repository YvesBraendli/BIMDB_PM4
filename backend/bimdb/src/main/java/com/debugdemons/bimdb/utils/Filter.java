package com.debugdemons.bimdb.utils;

import java.util.List;

public class Filter {

    private List<Integer> genresToInclude;

    private String latestReleaseDate;

    private Float minVoteAverage;

    private List<Long> actors;

    public List<Integer> getGenresToInclude() {
        return genresToInclude;
    }

    public void setGenresToInclude(List<Integer> genresToInclude) {
        this.genresToInclude = genresToInclude;
    }

    public String getLatestReleaseDate() {
        return latestReleaseDate;
    }

    public void setLatestReleaseDate(String latestReleaseDate) {
        this.latestReleaseDate = latestReleaseDate;
    }

    public Float getMinVoteAverage() {
        return minVoteAverage;
    }

    public void setMinVoteAverage(Float minVoteAverage) {
        this.minVoteAverage = minVoteAverage;
    }

    public List<Long> getActors() {
        return actors;
    }

    public void setActors(List<Long> actors) {
        this.actors = actors;
    }
}
