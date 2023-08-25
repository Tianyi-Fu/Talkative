package com.demo.serivce.impl;


import com.demo.repository.UserDao;
import com.demo.repository.impl.UserDaoImpl;
import com.demo.model.User;
import com.demo.serivce.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        return userDao.findUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User registUser= userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if(registUser!= null){
            //用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        userDao.save(user);
        return true;
    }
}
