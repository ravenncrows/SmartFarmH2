package com.smartfarmh2.user;

import com.smartfarmh2.role.Role;
import com.smartfarmh2.role.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 18/8/2559.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {



    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public List<User> findNewAccount() {
        return userDao.findNewAccount();
    }
}
