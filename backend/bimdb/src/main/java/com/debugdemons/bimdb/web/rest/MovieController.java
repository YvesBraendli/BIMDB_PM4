package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.MovieDetails;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.debugdemons.bimdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public MovieDetails getMovieDetails(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/{id}/watch-providers")
    public WatchProvidersResult getWatchProviders(@PathVariable Long id) {
        return movieService.getWatchProviders(id);
    }
}
