package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.service.MovieService;
import com.debugdemons.bimdb.service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/discover")
public class DiscoverController {

    private final MovieService movieService;

    private final TvService tvService;

    @Autowired
    public DiscoverController(MovieService movieService, TvService tvService) {
        this.movieService = movieService;
        this.tvService = tvService;
    }

    @GetMapping("/movies")
    public DiscoverMovie discoverMovies(@RequestParam(name = "page", required = false) Integer page) {
        //TODO: pass username
        return movieService.getMovies(page, "username");
    }

    @GetMapping("/tv")
    public DiscoverTv discoverTv(@RequestParam(name = "page", required = false) Integer page) {
        return tvService.getTv(page);
    }
}
