package com.debugdemons.bimdb.utils;

import java.util.List;

public class Filter {

    private List<Integer> genresToInclude;

    private List<Integer> genresToExclude;

    private Integer releaseYearFrom;

    private Integer releaseYearTo;

    private Double ratingThreshold;

    private List<Long> actors;

    public List<Integer> getGenresToInclude() {
        return genresToInclude;
    }

    public void setGenresToInclude(List<Integer> genresToInclude) {
        this.genresToInclude = genresToInclude;
    }

    public List<Integer> getGenresToExclude() {
        return genresToExclude;
    }

    public void setGenresToExclude(List<Integer> genresToExclude) {
        this.genresToExclude = genresToExclude;
    }

    public Integer getReleaseYearFrom() {
        return releaseYearFrom;
    }

    public void setReleaseYearFrom(Integer releaseYearFrom) {
        this.releaseYearFrom = releaseYearFrom;
    }

    public Integer getReleaseYearTo() {
        return releaseYearTo;
    }

    public void setReleaseYearTo(Integer releaseYearTo) {
        this.releaseYearTo = releaseYearTo;
    }

    public Double getRatingThreshold() {
        return ratingThreshold;
    }

    public void setRatingThreshold(Double ratingThreshold) {
        this.ratingThreshold = ratingThreshold;
    }

    public List<Long> getActors() {
        return actors;
    }

    public void setActors(List<Long> actors) {
        this.actors = actors;
    }
}
