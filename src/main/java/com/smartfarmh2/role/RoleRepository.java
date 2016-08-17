package com.smartfarmh2.role;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Asus on 18/8/2559.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String name);
}
