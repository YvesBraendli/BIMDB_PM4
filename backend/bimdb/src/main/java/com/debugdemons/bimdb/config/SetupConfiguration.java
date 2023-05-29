package com.debugdemons.bimdb.config;

import com.debugdemons.bimdb.model.User;
import com.debugdemons.bimdb.repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SetupConfiguration {

    private final UsersRepository usersRepository;

    @Autowired
    public SetupConfiguration(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostConstruct
    void createTestUsers() {
        createTestUser("englishExcludeAdultContentUser", Boolean.FALSE, "en", null, null);
        createTestUser("allFilterUser", Boolean.FALSE, "en", Boolean.TRUE, Boolean.TRUE);
        createTestUser("onlyBasicFilterUser", null, null, null, null);
        createTestUser("ratingFilterUser", null, null, null, Boolean.TRUE);
    }

    void createTestUser(String username, Boolean adult, String preferredOriginalLanguage, Boolean useDateFilters, Boolean useRatingFilter) {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
        }
        user.setAdult(adult);
        user.setPreferredOriginalLanguage(preferredOriginalLanguage);
        user.setUseDateFilter(useDateFilters);
        user.setUseRatingFilter(useRatingFilter);
        usersRepository.saveAndFlush(user);
    }
}
