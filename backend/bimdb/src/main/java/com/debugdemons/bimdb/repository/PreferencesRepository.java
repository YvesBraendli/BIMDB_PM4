package com.debugdemons.bimdb.repository;

import com.debugdemons.bimdb.model.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesRepository extends JpaRepository<UserPreferences, Long> {

    UserPreferences findByUsernameAndSource(String username, String source);
}
