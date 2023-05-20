package com.debugdemons.bimdb.repository;

import com.debugdemons.bimdb.model.UserPreferences;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class PreferencesRepositoryTest {

    @Autowired
    private PreferencesRepository preferencesRepository;

    @Test
    public void testFindByUsernameAndSource() {
        // Create test data
        UserPreferences preferences = new UserPreferences();
        preferences.setUsername("john");
        preferences.setSource("source1");
        List<Integer> favoriteTvGenres = Arrays.asList(1, 2, 3);
        preferences.setFavoriteTvGenres(favoriteTvGenres);
        List<Integer> favoriteMovieGenres = Arrays.asList(4, 5, 6);
        preferences.setFavoriteMovieGenres(favoriteMovieGenres);
        preferences.setReleaseYearFrom(2000);
        preferences.setReleaseYearTo(2022);
        preferences.setRatingThreshold(7.5);
        List<Long> favoriteActors = Arrays.asList(101L, 102L, 103L);
        preferences.setFavoriteActors(favoriteActors);
        preferencesRepository.save(preferences);

        // Perform repository query
        UserPreferences foundPreferences = preferencesRepository.findByUsernameAndSource("john", "source1");

        // Assert the result
        Assertions.assertNotNull(foundPreferences);
        Assertions.assertEquals("john", foundPreferences.getUsername());
        Assertions.assertEquals("source1", foundPreferences.getSource());
        Assertions.assertEquals(favoriteTvGenres, foundPreferences.getFavoriteTvGenres());
        Assertions.assertEquals(favoriteMovieGenres, foundPreferences.getFavoriteMovieGenres());
        Assertions.assertEquals(2000, foundPreferences.getReleaseYearFrom());
        Assertions.assertEquals(2022, foundPreferences.getReleaseYearTo());
        Assertions.assertEquals(7.5, foundPreferences.getRatingThreshold());
        Assertions.assertEquals(favoriteActors, foundPreferences.getFavoriteActors());
    }
}