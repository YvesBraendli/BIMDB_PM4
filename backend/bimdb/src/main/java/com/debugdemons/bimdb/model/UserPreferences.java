package com.debugdemons.bimdb.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_preferences")
public class UserPreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "favorite_tv_genres", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "genre")
    private List<Long> favoriteTvGenres;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tv_genres_to_exclude", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "genre")
    private List<Long> tvGenresToExclude;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "favorite_movie_genres", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "genre")
    private List<Long> favoriteMovieGenres;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_genres_to_exclude", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "genre")
    private List<Long> movieGenresToExclude;

    @Column(name = "release_year_from")
    private Integer releaseYearFrom;

    @Column(name = "release_year_to")
    private Integer releaseYearTo;

    @Column(name = "rating_threshold")
    private Double ratingThreshold;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "favorite_actors", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "actor")
    private List<Long> favoriteActors;

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

    public List<Long> getFavoriteTvGenres() {
        return favoriteTvGenres;
    }

    public void setFavoriteTvGenres(List<Long> favoriteTvGenres) {
        this.favoriteTvGenres = favoriteTvGenres;
    }

    public List<Long> getTvGenresToExclude() {
        return tvGenresToExclude;
    }

    public void setTvGenresToExclude(List<Long> tvGenresToExclude) {
        this.tvGenresToExclude = tvGenresToExclude;
    }

    public List<Long> getFavoriteMovieGenres() {
        return favoriteMovieGenres;
    }

    public void setFavoriteMovieGenres(List<Long> favoriteMovieGenres) {
        this.favoriteMovieGenres = favoriteMovieGenres;
    }

    public List<Long> getMovieGenresToExclude() {
        return movieGenresToExclude;
    }

    public void setMovieGenresToExclude(List<Long> movieGenresToExclude) {
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

    public List<Long> getFavoriteActors() {
        return favoriteActors;
    }

    public void setFavoriteActors(List<Long> favoriteActors) {
        this.favoriteActors = favoriteActors;
    }
}