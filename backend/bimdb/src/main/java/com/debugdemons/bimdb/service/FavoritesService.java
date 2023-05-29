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
    public void addFavorite(String username, Favorite favorite) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            usersRepository.saveAndFlush(user);
            user = usersRepository.findByUsername(username);
        }
        if (!favoritesRepository.existsByUserAndTypeAndApiId(user, favorite.getMediaType().getType(), favorite.getId())) {
            FavoriteMedia favoriteMovie = new FavoriteMedia(user, favorite.getMediaType().getType(), favorite.getId());
            favoritesRepository.save(favoriteMovie);
            getLogger().info("Adding favorite of type " + favorite.getMediaType().getType() + " with api id " + favorite.getId());
        }
    }

    @Transactional
    public void removeFavorite(String username, Favorite favorite) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            return;
        }
        Integer id = favoritesRepository.deleteByUserAndTypeAndApiId(user, favorite.getMediaType().getType(), favorite.getId());
        getLogger().info("Deleted favorite of type " + favorite.getMediaType().getType() + " with api id " + favorite.getId() + " and id " + id);
    }

    @Transactional
    public boolean isFavorite(String username, Favorite favorite) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        return favoritesRepository.existsByUserAndTypeAndApiId(user, favorite.getMediaType().getType(), favorite.getId());
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
