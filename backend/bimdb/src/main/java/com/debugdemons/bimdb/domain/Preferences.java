package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Preferences {

    @JsonProperty("username")
    private String username;

    @JsonProperty("favoriteTvGenres")
    private List<Genre> favoriteTvGenres;

    @JsonProperty("favoriteMovieGenres")
    private List<Genre> favoriteMovieGenres;

    @JsonProperty("releaseYearFrom")
    private Integer releaseYearFrom;

    @JsonProperty("releaseYearTo")
    private Integer releaseYearTo;

    @JsonProperty("ratingThreshold")
    private Double ratingThreshold;

    @JsonProperty("language")
    private Language language;

    @JsonProperty("favoriteActors")
    private List<Person> favoriteActors;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Genre> getFavoriteTvGenres() {
        return favoriteTvGenres;
    }

    public void setFavoriteTvGenres(List<Genre> favoriteTvGenres) {
        this.favoriteTvGenres = favoriteTvGenres;
    }

    public List<Genre> getFavoriteMovieGenres() {
        return favoriteMovieGenres;
    }

    public void setFavoriteMovieGenres(List<Genre> favoriteMovieGenres) {
        this.favoriteMovieGenres = favoriteMovieGenres;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Person> getFavoriteActors() {
        return favoriteActors;
    }

    public void setFavoriteActors(List<Person> favoriteActors) {
        this.favoriteActors = favoriteActors;
    }
}
