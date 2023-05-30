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
        createTestUser("englishexcludeadultcontentuser", Boolean.FALSE, "en", null, null);
        createTestUser("allfilteruser", Boolean.FALSE, "en", Boolean.TRUE, Boolean.TRUE);
        createTestUser("onlybasicfilteruser", null, null, null, null);
        createTestUser("ratingfilteruser", null, null, null, Boolean.TRUE);
        createTestUser("dateandratingfilteruser", null, null, Boolean.TRUE, Boolean.TRUE);
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
        usersRepository.findByUsername(username);
    }
}
