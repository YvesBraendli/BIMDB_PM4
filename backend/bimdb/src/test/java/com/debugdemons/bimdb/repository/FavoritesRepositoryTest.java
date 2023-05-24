package com.debugdemons.bimdb.repository;

import com.debugdemons.bimdb.model.FavoriteMedia;
import com.debugdemons.bimdb.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@SpringJUnitConfig
class FavoritesRepositoryTest {

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testExistsByUserAndTypeAndApiId() {
        // Create test data
        User user = new User(); // Create a User object
        user.setUsername("username");

        FavoriteMedia favorite = new FavoriteMedia(user, "movie", 100L);

        entityManager.persist(user);
        entityManager.persist(favorite);
        entityManager.flush();

        // Call the method to be tested
        boolean exists = favoritesRepository.existsByUserAndTypeAndApiId(user, "movie", 100L);

        // Assert the result
        Assertions.assertTrue(exists);
    }

    @Test
    void testDeleteByUserAndTypeAndApiId() {
        // Create test data
        User user = new User(); // Create a User object
        user.setUsername("username");

        FavoriteMedia favorite = new FavoriteMedia(user, "movie", 100L);

        entityManager.persist(user);
        entityManager.persist(favorite);
        entityManager.flush();

        // Call the method to be tested
        Integer deleteCount = favoritesRepository.deleteByUserAndTypeAndApiId(user, "movie", 100L);

        // Assert the result
        Assertions.assertEquals(1, deleteCount);
    }

    @Test
    void testFindAllApiIdsByUserAndType() {
        // Create test data
        User user = new User(); // Create a User object
        user.setUsername("username");

        FavoriteMedia favorite1 = new FavoriteMedia(user, "movie", 100L);

        FavoriteMedia favorite2 = new FavoriteMedia(user, "tv", 200L);

        entityManager.persist(user);
        entityManager.persist(favorite1);
        entityManager.persist(favorite2);
        entityManager.flush();

        // Call the method to be tested
        Set<Long> apiIds = favoritesRepository.findAllApiIdsByUserAndType(user, "movie");

        // Assert the result
        Set<Long> expectedApiIds = new HashSet<>();
        expectedApiIds.add(100L);
        Assertions.assertEquals(expectedApiIds, apiIds);
    }


    @Test
    void testFindAllByUserAndType() {
        // Create test data
        User user = new User(); // Create a User object
        user.setUsername("username");

        FavoriteMedia favorite1 = new FavoriteMedia(user, "movie", 100L);

        FavoriteMedia favorite2 = new FavoriteMedia(user, "tv", 200L);

        entityManager.persist(user);
        entityManager.persist(favorite1);
        entityManager.persist(favorite2);
        entityManager.flush();

        // Call the method to be tested
        List<FavoriteMedia> favoriteMediaList = favoritesRepository.findAllByUserAndType(user, "movie");

        // Assert the result
        FavoriteMedia actualFavoriteMedia = favoriteMediaList.get(0);
        Assertions.assertEquals(favorite1.getUser(), actualFavoriteMedia.getUser());
        Assertions.assertEquals(favorite1.getType(), actualFavoriteMedia.getType());
        Assertions.assertEquals(favorite1.getApiId(), actualFavoriteMedia.getApiId());

    }
}