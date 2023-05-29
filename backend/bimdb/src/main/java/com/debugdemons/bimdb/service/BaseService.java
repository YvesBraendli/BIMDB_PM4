package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.utils.Filter;
import org.springframework.web.client.RestTemplate;

public abstract class BaseService {

    protected final MovieDBApiConfig movieDBApiConfig;

    protected final RestTemplate restTemplate;

    protected BaseService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        this.movieDBApiConfig = movieDBApiConfig;
        this.restTemplate = restTemplate;
    }

    protected void applyFilter(User user, TmdbUrlBuilder tmdbUrlBuilder, Filter filter) {
        tmdbUrlBuilder.withGenres(filter.getGenresToInclude())
                .withCast(filter.getActors());
        if (user.getUseDateFilter() != null) {
            tmdbUrlBuilder.withPrimaryReleaseDateLte(filter.getLatestReleaseDate());
        }
        if (user.getUseRatingFilter() != null) {
            tmdbUrlBuilder.withVoteAverageGte(filter.getMinVoteAverage());
        }
    }

}
