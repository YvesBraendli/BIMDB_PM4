package com.debugdemons.bimdb.service;

import java.util.Collection;
import java.util.List;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class TmdbUrlBuilder {

    private static final String OR_DELIMITER = "|";
    private static final String AND_DELIMITER = ",";
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

    public TmdbUrlBuilder withGenres(List<Integer> genres) {
        if (genres != null && !genres.isEmpty()) {
            uriBuilder.queryParam("with_genres", getParamValue(AND_DELIMITER, genres));
        }
        return this;
    }

    public TmdbUrlBuilder withCast(List<Long> actors) {
        if (actors != null && !actors.isEmpty()) {
            uriBuilder.queryParam("with_cast", getParamValue(OR_DELIMITER, actors));
        }
        return this;
    }

    public String build() {
        UriComponents uriComponents = uriBuilder.build();
        return uriComponents.toUriString();
    }

    private <T> String getParamValue(String delimiter, Collection<T> values) {
        return String.join(delimiter, values.stream().map(String::valueOf).toArray(String[]::new));
    }
}