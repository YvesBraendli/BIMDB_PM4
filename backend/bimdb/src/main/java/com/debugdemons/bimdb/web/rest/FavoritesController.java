package com.debugdemons.bimdb.web.rest;

import com.debugdemons.bimdb.domain.Favorite;
import com.debugdemons.bimdb.domain.FavoriteResponse;
import com.debugdemons.bimdb.domain.MediaType;
import com.debugdemons.bimdb.security.JwtUtil;
import com.debugdemons.bimdb.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;

    private final JwtUtil jwtUtil;

    @Autowired
    public FavoritesController(FavoritesService favoritesService, JwtUtil jwtUtil) {
        this.favoritesService = favoritesService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public FavoriteResponse addFavorite(Principal principal, @RequestBody Favorite favorite) {
        favoritesService.saveNewFavorite(jwtUtil.getUsernameFromJWT(principal), favorite);
        return new FavoriteResponse(true);
    }

    @DeleteMapping
    public FavoriteResponse removeFavorite(Principal principal,
                                           @RequestParam(name = "id") Long id,
                                           @RequestParam(name = "mediaType") String mediaType) {

        favoritesService.removeFavorite(jwtUtil.getUsernameFromJWT(principal),
                                        new Favorite(MediaType.fromString(mediaType), id));

        return new FavoriteResponse(false);
    }

    @GetMapping
    public FavoriteResponse isFavorite(Principal principal,
                                       @RequestParam(name = "id") Long id,
                                       @RequestParam(name = "mediaType") String mediaType) {

        return new FavoriteResponse(favoritesService.isFavorite(jwtUtil.getUsernameFromJWT(principal),
                                    new Favorite(MediaType.fromString(mediaType), id)));
    }

    @GetMapping("/all")
    public List<Favorite> getFavorites(Principal principal,
                                       @RequestParam(name = "mediaType") String mediaType) {

        return favoritesService.getAllFavoritesByType(jwtUtil.getUsernameFromJWT(principal), MediaType.fromString(mediaType));
    }

}
