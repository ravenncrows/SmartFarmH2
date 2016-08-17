package com.smartfarmh2.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Asus on 18/8/2559.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}

