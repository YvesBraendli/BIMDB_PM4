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
        String movieGenresUrl = movieDBApiConfig.getBaseUrl() + "genre/movie/list";
        return restTemplate.getForObject(movieGenresUrl, GenreListWrapper.class);
    }

    public GenreListWrapper getAllTvGenres() {
        String tvGenresUrl = movieDBApiConfig.getBaseUrl() + "genre/tv/list";
        return restTemplate.getForObject(tvGenresUrl, GenreListWrapper.class);
    }
}
