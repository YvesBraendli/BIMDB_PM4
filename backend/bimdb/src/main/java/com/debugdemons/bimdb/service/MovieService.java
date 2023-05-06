package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.config.MovieDBApiConfig;
import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService extends BaseService {

    public MovieService(MovieDBApiConfig movieDBApiConfig, RestTemplate restTemplate) {
        super(movieDBApiConfig, restTemplate);
    }


    public DiscoverMovie getMovies(Integer page) {
        String url = movieDBApiConfig.getBaseUrl() + "discover/movie";
        if (page != null) {
            url += "?page=" + page + "&";
        } else {
            url += "?";
        }
        url += "api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, DiscoverMovie.class);
    }

    public MovieDetails getMovieById(Long id) {
        String url = movieDBApiConfig.getBaseUrl() + "movie/" + id + "?api_key=" + movieDBApiConfig.getApiKey();
        return restTemplate.getForObject(url, MovieDetails.class);
    }
}
