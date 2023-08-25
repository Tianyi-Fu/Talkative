package com.demo.serivce;


import com.demo.model.User;

public interface UserService {

    User login(User user);

    boolean regist(User user);
}
