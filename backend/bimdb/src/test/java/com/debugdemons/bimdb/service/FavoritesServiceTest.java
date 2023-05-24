package com.debugdemons.bimdb.service;

import com.debugdemons.bimdb.domain.Favorite;
import com.debugdemons.bimdb.domain.MediaType;
import com.debugdemons.bimdb.model.FavoriteMedia;
import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.repository.FavoritesRepository;
import com.debugdemons.bimdb.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

class FavoritesServiceTest extends BaseServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private FavoritesRepository favoritesRepository;

    private FavoritesService favoritesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        favoritesService = new FavoritesService(usersRepository, favoritesRepository);
    }

    @Test
    void testSaveNewFavoriteExistingUser() {
        String username = "testUser";
        Favorite favorite = new Favorite(MediaType.MOVIE, 100L);

        User user = new User();
        user.setUsername(username);

        // Mocking the behavior for the first invocation of existsByUserAndTypeAndApiId
        Mockito.when(usersRepository.findByUsername(username)).thenReturn(user);
        Mockito.when(favoritesRepository.existsByUserAndTypeAndApiId(any(User.class), anyString(), anyLong())).thenReturn(false);

        favoritesService.saveNewFavorite(username, favorite);

        Mockito.verify(usersRepository).findByUsername(username);
        Mockito.verify(favoritesRepository).existsByUserAndTypeAndApiId(any(User.class), anyString(), anyLong());
        Mockito.verify(favoritesRepository).save(any(FavoriteMedia.class));
    }

    @Test
    void testSaveNewFavorite() {
        String username = "testUser";
        Favorite favorite = new Favorite(MediaType.MOVIE, 100L);

        User user = new User();
        user.setUsername(username);

        // Mocking the behavior for the first invocation of existsByUserAndTypeAndApiId
        Mockito.when(usersRepository.findByUsername(username)).thenReturn(null);
        Mockito.when(usersRepository.saveAndFlush(user)).thenReturn(user);
        Mockito.when(favoritesRepository.existsByUserAndTypeAndApiId(any(User.class), anyString(), anyLong())).thenReturn(false);

        favoritesService.saveNewFavorite(username, favorite);

        Mockito.verify(usersRepository, Mockito.times(2)).findByUsername(username);
        Mockito.verify(usersRepository).saveAndFlush(any(User.class));
        Mockito.verify(favoritesRepository).save(any(FavoriteMedia.class));
    }

    @Test
    void testRemoveFavorite() {
        String username = "testUser";
        Favorite favorite = new Favorite(MediaType.MOVIE, 100L);


        User user = new User();
        user.setUsername(username);
        Mockito.when(usersRepository.findByUsername(username)).thenReturn(user);

        favoritesService.removeFavorite(username, favorite);

        Mockito.verify(usersRepository).findByUsername(username);
        Mockito.verify(favoritesRepository).deleteByUserAndTypeAndApiId(any(User.class), anyString(), anyLong());
    }

    @Test
    void testIsFavorite() {
        String username = "testUser";
        Favorite favorite = new Favorite(MediaType.MOVIE, 100L);


        User user = new User();
        user.setUsername(username);
        Mockito.when(usersRepository.findByUsername(username)).thenReturn(user);
        Mockito.when(favoritesRepository.existsByUserAndTypeAndApiId(any(User.class), anyString(), anyLong())).thenReturn(true);

        boolean isFavorite = favoritesService.isFavorite(username, favorite);

        Assertions.assertTrue(isFavorite);

        Mockito.verify(usersRepository).findByUsername(username);
        Mockito.verify(favoritesRepository).existsByUserAndTypeAndApiId(any(User.class), anyString(), anyLong());
    }

    @Test
    void testGetAllFavoritesByType() {
        String username = "testUser";

        User user = new User();
        user.setUsername(username);
        Mockito.when(usersRepository.findByUsername(username)).thenReturn(user);

        List<FavoriteMedia> favoriteList = new ArrayList<>();
        FavoriteMedia favoriteMedia = new FavoriteMedia(user, "movie", 100L);
        favoriteList.add(favoriteMedia);

        Mockito.when(favoritesRepository.findAllByUserAndType(any(User.class), anyString())).thenReturn(favoriteList);

        List<Favorite> expectedFavorites = new ArrayList<>();
        Favorite favorite = new Favorite(MediaType.MOVIE, 100L);
        expectedFavorites.add(favorite);


        List<Favorite> result = favoritesService.getAllFavoritesByType(username, MediaType.MOVIE);

        Assertions.assertEquals(expectedFavorites.get(0).getId(), result.get(0).getId());
        Assertions.assertEquals(expectedFavorites.get(0).getMediaType(), result.get(0).getMediaType());

        Mockito.verify(usersRepository).findByUsername(username);
        Mockito.verify(favoritesRepository).findAllByUserAndType(any(User.class), anyString());
    }
}
