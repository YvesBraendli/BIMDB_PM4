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

    @Column(name = "source")
    private String source;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "favorite_tv_genres", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "genre")
    private List<Integer> favoriteTvGenres;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "favorite_movie_genres", joinColumns = @JoinColumn(name = "user_preferences_id"))
    @Column(name = "genre")
    private List<Integer> favoriteMovieGenres;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Integer> getFavoriteTvGenres() {
        return favoriteTvGenres;
    }

    public void setFavoriteTvGenres(List<Integer> favoriteTvGenres) {
        this.favoriteTvGenres = favoriteTvGenres;
    }

    public List<Integer> getFavoriteMovieGenres() {
        return favoriteMovieGenres;
    }

    public void setFavoriteMovieGenres(List<Integer> favoriteMovieGenres) {
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

    public List<Long> getFavoriteActors() {
        return favoriteActors;
    }

    public void setFavoriteActors(List<Long> favoriteActors) {
        this.favoriteActors = favoriteActors;
    }
}