package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    @Autowired
    private MovieDBApiConfig movieDBApiConfig;

    @Autowired
    private RestTemplate restTemplate;

    public DiscoverMovie getMovies() {
        String url = movieDBApiConfig.getBaseUrl() + "discover/movie?api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, DiscoverMovie.class);
    }

    public MovieDetails getMovieById(Long id) {
        String url = movieDBApiConfig.getBaseUrl() + "movie/" + id + "?api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, MovieDetails.class);
    }
}
