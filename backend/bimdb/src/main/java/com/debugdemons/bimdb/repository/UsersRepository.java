package com.debugdemons.bimdb.repository;

import com.debugdemons.bimdb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
