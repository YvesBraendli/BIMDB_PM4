package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.Favorite;
import com.debugdemons.bimdb.service.PreferencesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/favorites")
public class FavoritesController {

    private final PreferencesService preferencesService;

    public FavoritesController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    @PostMapping
    public void addFavorite(@RequestBody Favorite favorite) {
        ///TODO: get username from header
        preferencesService.saveNewFavorite("username", favorite);
    }

    @DeleteMapping
    public void removeFavorite(@RequestBody Favorite favorite) {
        ///TODO: get username from header
        preferencesService.removeFavorite("username", favorite);
    }

}
