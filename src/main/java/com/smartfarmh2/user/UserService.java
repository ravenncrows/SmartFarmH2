package com.smartfarmh2.user;


import java.util.List;

/**
 * Created by Asus on 18/8/2559.
 */
public interface UserService{
    public List<User> findAll();
    public User findByUserName(String username);
    public User create(User user);
    List<User> findNewAccount();
}