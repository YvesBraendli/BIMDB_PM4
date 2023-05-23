package com.debugdemons.bimdb.repository;

import com.debugdemons.bimdb.model.UserPreferences;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
class PreferencesRepositoryTest {

    @Autowired
    private PreferencesRepository preferencesRepository;

    @Test
    void testFindByUsername() {
        // Create test data
        UserPreferences preferences = new UserPreferences();
        preferences.setUsername("john");
        List<Long> favoriteTvGenres = Arrays.asList(1L, 2L, 3L);
        preferences.setFavoriteTvGenres(favoriteTvGenres);
        List<Long> favoriteMovieGenres = Arrays.asList(4L, 5L, 6L);
        preferences.setFavoriteMovieGenres(favoriteMovieGenres);
        preferences.setReleaseYearFrom(2000);
        preferences.setReleaseYearTo(2022);
        preferences.setRatingThreshold(7.5);
        Set<Long> favoriteActors = new HashSet<>(Set.of(101L, 102L, 103L));
        preferences.setFavoriteActors(favoriteActors);
        preferencesRepository.save(preferences);

        // Perform repository query
        UserPreferences foundPreferences = preferencesRepository.findByUsername("john");

        // Assert the result
        Assertions.assertNotNull(foundPreferences);
        Assertions.assertEquals("john", foundPreferences.getUsername());
        Assertions.assertEquals(favoriteTvGenres, foundPreferences.getFavoriteTvGenres());
        Assertions.assertEquals(favoriteMovieGenres, foundPreferences.getFavoriteMovieGenres());
        Assertions.assertEquals(2000, foundPreferences.getReleaseYearFrom());
        Assertions.assertEquals(2022, foundPreferences.getReleaseYearTo());
        Assertions.assertEquals(7.5, foundPreferences.getRatingThreshold());
        Assertions.assertEquals(favoriteActors, foundPreferences.getFavoriteActors());
    }
}