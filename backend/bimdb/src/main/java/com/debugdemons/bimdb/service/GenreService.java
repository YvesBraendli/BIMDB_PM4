package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.Genre;
import com.debugdemons.bimdb.domain.GenreListWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GenreService extends BaseService {

    protected GenreService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        super(movieDBApiConfig, restTemplate);
    }

    public List<Genre> getAllMovieGenres() {
        String movieGenresUrl = movieDBApiConfig.getBaseUrl() + "genre/movie/list";
        return restTemplate.getForObject(movieGenresUrl, GenreListWrapper.class).getGenres();
    }

    public List<Genre> getAllTvGenres() {
        String tvGenresUrl = movieDBApiConfig.getBaseUrl() + "genre/tv/list";
        return restTemplate.getForObject(tvGenresUrl, GenreListWrapper.class).getGenres();
    }
}
