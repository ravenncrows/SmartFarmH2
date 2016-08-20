package com.smartfarmh2.user;

import com.smartfarmh2.role.Role;
import com.smartfarmh2.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 18/8/2559.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        Role newUser = roleRepository.findByRoleName("newUser");
        Set<Role> roles = new HashSet<>();
        roles.add(newUser);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findNewAccount() {
        Role newUser = roleRepository.findByRoleName("newUser");
        Set<Role> roles = new HashSet<>();
        roles.add(newUser);
        return userRepository.findByRoles(roles);
    }
}
