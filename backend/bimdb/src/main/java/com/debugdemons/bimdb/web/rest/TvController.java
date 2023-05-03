package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.TvShowDetails;
import com.debugdemons.bimdb.service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tv")
public class TvController {

    @Autowired
    private final TvService movieService;

    public TvController(TvService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public TvShowDetails getMovieDetails(@PathVariable Long id) {
        return movieService.getTvShowById(id);
    }
}

