package com.debugdemons.bimdb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Preferences implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("favoriteTvGenres")
    private List<Genre> favoriteTvGenres;

    @JsonProperty("tvGenresToExclude")
    private List<Genre> tvGenresToExclude;

    @JsonProperty("favoriteMovieGenres")
    private List<Genre> favoriteMovieGenres;

    @JsonProperty("movieGenresToExclude")
    private List<Genre> movieGenresToExclude;

    @JsonProperty("releaseYearFrom")
    private Integer releaseYearFrom;

    @JsonProperty("releaseYearTo")
    private Integer releaseYearTo;

    @JsonProperty("ratingThreshold")
    private Double ratingThreshold;

    @JsonProperty("favoriteActors")
    private List<Person> favoriteActors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Genre> getTvGenresToExclude() {
        return tvGenresToExclude;
    }

    public void setTvGenresToExclude(List<Genre> tvGenresToExclude) {
        this.tvGenresToExclude = tvGenresToExclude;
    }

    public List<Genre> getFavoriteMovieGenres() {
        return favoriteMovieGenres;
    }

    public void setFavoriteMovieGenres(List<Genre> favoriteMovieGenres) {
        this.favoriteMovieGenres = favoriteMovieGenres;
    }

    public List<Genre> getMovieGenresToExclude() {
        return movieGenresToExclude;
    }

    public void setMovieGenresToExclude(List<Genre> movieGenresToExclude) {
        this.movieGenresToExclude = movieGenresToExclude;
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

    public List<Person> getFavoriteActors() {
        return favoriteActors;
    }

    public void setFavoriteActors(List<Person> favoriteActors) {
        this.favoriteActors = favoriteActors;
    }
}
