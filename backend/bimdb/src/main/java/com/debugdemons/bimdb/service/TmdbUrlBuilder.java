package com.debugdemons.bimdb.service;

import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class TmdbUrlBuilder {

    private static final String OR_DELIMITER = "|";
    private static final String AND_DELIMITER = ",";
    private UriComponentsBuilder uriBuilder;

    public TmdbUrlBuilder(String baseUrl) {
        this.uriBuilder = UriComponentsBuilder.fromUriString(baseUrl);
    }

    public TmdbUrlBuilder withEndpoint(String endpoint) {
        if (StringUtils.hasText(endpoint)) {
            uriBuilder.path(endpoint);
        }
        return this;
    }

    public TmdbUrlBuilder withPage(Integer page) {
        if (page != null) {
            uriBuilder.queryParam("page", page);
        }
        return this;
    }

    public TmdbUrlBuilder withGenres(List<Integer> genres) {
        if (!CollectionUtils.isEmpty(genres)) {
            uriBuilder.queryParam("with_genres", getParamValue(OR_DELIMITER, genres));
        }
        return this;
    }

    public TmdbUrlBuilder withCast(List<Long> actors) {
        if (!CollectionUtils.isEmpty(actors)) {
            uriBuilder.queryParam("with_cast", getParamValue(OR_DELIMITER, actors));
        }
        return this;
    }

    public TmdbUrlBuilder withOriginalLanguage(String language) {
        if (StringUtils.hasText(language)) {
            uriBuilder.queryParam("with_original_language", language);
        }
        return this;
    }

    public TmdbUrlBuilder withAppendToResponse(List<String> elementsToAppend) {
        if (!CollectionUtils.isEmpty(elementsToAppend)) {
            uriBuilder.queryParam("append_to_response", getParamValue(AND_DELIMITER, elementsToAppend));
        }
        return this;
    }

    public TmdbUrlBuilder withQuery(String query) {
        if (StringUtils.hasText(query)) {
            uriBuilder.queryParam("query", query);
        }
        return this;
    }

    public TmdbUrlBuilder withIncludeAdult(Boolean includeAdult) {
        if (includeAdult != null) {
            uriBuilder.queryParam("include_adult", includeAdult.toString());
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