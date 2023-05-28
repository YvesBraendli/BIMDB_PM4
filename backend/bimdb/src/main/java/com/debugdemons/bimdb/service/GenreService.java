package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.GenreListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenreService extends BaseService {

    protected GenreService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        super(movieDBApiConfig, restTemplate);
    }

    public GenreListWrapper getAllMovieGenres() {
        return restTemplate.getForObject(new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl()).withEndpoint("genre/movie/list").build(), GenreListWrapper.class);
    }

    public GenreListWrapper getAllTvGenres() {
        return restTemplate.getForObject(new TmdbUrlBuilder(movieDBApiConfig.getBaseUrl()).withEndpoint("genre/tv/list").build(), GenreListWrapper.class);
    }
}
