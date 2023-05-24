package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.TvShowDetails;
import com.debugdemons.bimdb.domain.TvShowSeasonDetails;
import com.debugdemons.bimdb.domain.WatchProvidersResult;
import com.debugdemons.bimdb.service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tv")
public class TvController {

    private final TvService tvService;

    @Autowired
    public TvController(TvService tvService) {
        this.tvService = tvService;
    }

    @GetMapping("/{id}")
    public TvShowDetails getTvShowDetails(@PathVariable Long id) {
        return tvService.getTvShowById(id);
    }

    @GetMapping("/{id}/{seasonNumber}")
    public TvShowSeasonDetails getTvShowSeasonDetails(@PathVariable Long id, @PathVariable Long seasonNumber) {
        return tvService.getTvShowSeasonDetails(id, seasonNumber);
    }

    @GetMapping("/{id}/watch-providers")
    public WatchProvidersResult getWatchProviders(@PathVariable Long id) {
        return tvService.getWatchProviders(id);
    }
}

