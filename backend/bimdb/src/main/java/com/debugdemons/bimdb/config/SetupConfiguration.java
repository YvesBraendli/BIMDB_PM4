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
        User user = usersRepository.findByUsername("englishExcludeAdultContent");
        if (user == null) {
            user = new User();
            user.setUsername("englishExcludeAdultContent");
        }
        user.setAdult(false);
        user.setPreferredOriginalLanguage("en");
        usersRepository.saveAndFlush(user);
    }
}
