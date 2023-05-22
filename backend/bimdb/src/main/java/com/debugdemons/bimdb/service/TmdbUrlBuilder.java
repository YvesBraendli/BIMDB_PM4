package com.debugdemons.bimdb.service;

import java.util.List;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class TmdbUrlBuilder {

    private static final String OR_DELIMITER = "%7C";
    private UriComponentsBuilder uriBuilder;

    public TmdbUrlBuilder(String baseUrl, String specificUrl) {
        uriBuilder = UriComponentsBuilder.fromUriString(baseUrl + specificUrl);
    }

    public TmdbUrlBuilder withPageNumber(Integer page) {
        if (page != null) {
            uriBuilder.queryParam("page", page);
        }
        return this;
    }

    public TmdbUrlBuilder withFavoriteTvGenres(List<Long> favoriteTvGenres) {
        if (favoriteTvGenres != null && !favoriteTvGenres.isEmpty()) {
            uriBuilder.queryParam("with_genres", getParamValue(OR_DELIMITER, favoriteTvGenres));
        }
        return this;
    }

    public TmdbUrlBuilder withFavoriteMovieGenres(List<Long> favoriteMovieGenres) {
        if (favoriteMovieGenres != null && !favoriteMovieGenres.isEmpty()) {
            uriBuilder.queryParam("with_genres", getParamValue(OR_DELIMITER, favoriteMovieGenres));
        }
        return this;
    }

    public TmdbUrlBuilder withCast(List<Long> favoriteActors) {
        if (favoriteActors != null && !favoriteActors.isEmpty()) {
            uriBuilder.queryParam("with_cast", getParamValue(OR_DELIMITER, favoriteActors));
        }
        return this;
    }

    public TmdbUrlBuilder withSortBy() {
        uriBuilder.queryParam("sort_by", "");
        return this;
    }

    public String build() {
        UriComponents uriComponents = uriBuilder.build();
        return uriComponents.toUriString();
    }

    private <T> String getParamValue(String delimiter, List<T> values) {
        return String.join(delimiter, values.stream().map(String::valueOf).toArray(String[]::new));
    }
}