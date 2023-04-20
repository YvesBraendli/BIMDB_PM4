package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/discover")
public class DiscoverController {

    @Autowired
    private final MovieService movieService;

    public DiscoverController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public DiscoverMovie discoverMovies(@RequestParam Map<String, String> params) {
        return movieService.getMovies();
    }
}
