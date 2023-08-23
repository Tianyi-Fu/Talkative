package com.demo.repository;


import com.demo.model.User;

public interface UserDao {

    public User findUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    void save(User user);
}
