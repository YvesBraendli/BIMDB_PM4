package com.debugdemons.bimdb.repository;

import com.debugdemons.bimdb.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void testFindByUsername() {
        // Create test data
        User expectedUser = new User();
        expectedUser.setUsername("john");
       usersRepository.save(expectedUser);

        // Perform repository query
        User user = usersRepository.findByUsername("john");

        // Assert the result
        Assertions.assertNotNull(user);
        Assertions.assertEquals(expectedUser.getUsername(), user.getUsername());
        Assertions.assertNull(user.getAdult());
        Assertions.assertNull(user.getPreferredOriginalLanguage());
    }

    @Test
    void testFindByUsernameWithAttributes() {
        // Create test data
        User expectedUser = new User();
        expectedUser.setUsername("hans");
        expectedUser.setAdult(Boolean.FALSE);
        expectedUser.setPreferredOriginalLanguage("en");
        usersRepository.save(expectedUser);

        // Perform repository query
        User user = usersRepository.findByUsername("hans");

        // Assert the result
        Assertions.assertNotNull(user);
        Assertions.assertEquals(expectedUser.getUsername(), user.getUsername());
        Assertions.assertEquals(expectedUser.getAdult(), user.getAdult());
        Assertions.assertEquals(expectedUser.getPreferredOriginalLanguage(), user.getPreferredOriginalLanguage());
    }

}