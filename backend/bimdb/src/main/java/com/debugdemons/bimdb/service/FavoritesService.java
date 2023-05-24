package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.Favorite;
import com.debugdemons.bimdb.domain.MediaType;
import com.debugdemons.bimdb.model.FavoriteMedia;
import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.repository.FavoritesRepository;
import com.debugdemons.bimdb.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class FavoritesService {

    private UsersRepository usersRepository;

    private FavoritesRepository favoritesRepository;

    @Autowired
    public FavoritesService(UsersRepository usersRepository, FavoritesRepository favoritesRepository) {
        this.usersRepository = usersRepository;
        this.favoritesRepository = favoritesRepository;
    }

    @Transactional
    public void saveNewFavorite(String username, Favorite favorite) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            usersRepository.saveAndFlush(user);
            user = usersRepository.findByUsername(username);
        }
        boolean isFavorite = favoritesRepository.existsByUserAndTypeAndApiId(user, favorite.getMediaType().getType(), favorite.getId());
        if (!isFavorite) {
            FavoriteMedia favoriteMovie = new FavoriteMedia(user, favorite.getMediaType().getType(), favorite.getId());
            favoritesRepository.save(favoriteMovie);
            getLogger().info("Adding favorite");
        }
    }

    @Transactional
    public void removeFavorite(String username, Favorite favorite) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            return;
        }
        Integer id = favoritesRepository.deleteByUserAndTypeAndApiId(user, favorite.getMediaType().getType(), favorite.getId());
        getLogger().info("deleted: " + id);
    }

    @Transactional
    public boolean isFavorite(String username, Favorite favorite) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        boolean isFavorite = favoritesRepository.existsByUserAndTypeAndApiId(user, favorite.getMediaType().getType(), favorite.getId());
        getLogger().info("Is favorite: " + isFavorite);
        return isFavorite;
    }

    @Transactional
    public List<Favorite> getAllFavoritesByType(String username, MediaType mediaType) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            return Collections.emptyList();
        }
        List<FavoriteMedia> favoriteMediaList = favoritesRepository.findAllByUserAndType(user, mediaType.getType());
        List<Favorite> favorites = new ArrayList<>();
        for (FavoriteMedia favoriteMedia : favoriteMediaList) {
            favorites.add(new Favorite(MediaType.fromString(favoriteMedia.getType()), favoriteMedia.getApiId()));
        }
        return favorites;
    }

}
