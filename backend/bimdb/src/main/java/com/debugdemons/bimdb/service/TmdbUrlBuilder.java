package com.debugdemons.bimdb.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TmdbUrlBuilder {

    private StringBuilder urlBuilder;

    public TmdbUrlBuilder(String baseUrl, String specificUrl) {
        urlBuilder = new StringBuilder(baseUrl);
        urlBuilder.append(specificUrl);
    }

    public TmdbUrlBuilder withPageNumber(Integer page) {
        if (page != null) {
            appendQueryParam("page", new Integer[]{page});
        }
        return this;
    }

    public TmdbUrlBuilder withGenres(List<Integer> favoriteMovieGenres) {
        appendQueryParam("with_genres", favoriteMovieGenres.toArray());
        return this;
    }

    public TmdbUrlBuilder withCast(List<Long> favoriteActors) {
        appendQueryParam("with_cast", favoriteActors.toArray());
        return this;
    }

    public TmdbUrlBuilder withSortBy() {
        appendQueryParam("sort_by", new String[]{""});
        return this;
    }

    public String build() {
        return urlBuilder.toString();
    }

    private void appendQueryParam(String paramName, Object[] paramValues) {
        if (paramValues != null && paramValues.length > 0) {
            urlBuilder.append(urlBuilder.indexOf("?") >= 0 ? "&" : "?");
            urlBuilder.append(paramName).append("=").append(
                    Arrays.stream(paramValues)
                            .map(String::valueOf)
                            .collect(Collectors.joining("%7C"))
            );
        }
    }
}
