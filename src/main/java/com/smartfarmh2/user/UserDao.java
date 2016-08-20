package com.smartfarmh2.user;

import java.util.List;

/**
 * Created by Asus on 18/8/2559.
 */
public interface UserDao {
    public List<User> findAll();
    public User create(User user);
    public User update(User user);
    public void delete(User user);
    public User findByUsername(String username);
    List<User> findNewAccount();
}