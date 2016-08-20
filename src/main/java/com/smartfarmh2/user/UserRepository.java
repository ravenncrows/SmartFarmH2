package com.smartfarmh2.user;

import com.smartfarmh2.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 18/8/2559.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findByRoles(Set<Role> roles);
}

