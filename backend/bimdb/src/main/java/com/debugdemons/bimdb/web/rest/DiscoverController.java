package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.DiscoverMovie;
import com.debugdemons.bimdb.domain.DiscoverTv;
import com.debugdemons.bimdb.security.JwtUtil;
import com.debugdemons.bimdb.service.MovieService;
import com.debugdemons.bimdb.service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/discover")
public class DiscoverController {

    private final MovieService movieService;

    private final TvService tvService;

    private final JwtUtil jwtUtil;

    @Autowired
    public DiscoverController(MovieService movieService, TvService tvService, JwtUtil jwtUtil) {
        this.movieService = movieService;
        this.tvService = tvService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/movies")
    public DiscoverMovie discoverMovies(Principal principal, @RequestParam(name = "page", required = false) Integer page) {
        return movieService.getMovies(page, jwtUtil.getUsernameFromJWT(principal));
    }

    @GetMapping("/tv")
    public DiscoverTv discoverTv(Principal principal, @RequestParam(name = "page", required = false) Integer page) {
        return tvService.getTv(page, jwtUtil.getUsernameFromJWT(principal));
    }
}
