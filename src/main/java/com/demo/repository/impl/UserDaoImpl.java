package com.demo.repository.impl;


import com.demo.repository.UserDao;
import com.demo.model.User;
import com.demo.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class UserDaoImpl implements UserDao {

    //声明JdbcTemplate对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名和密码进行查找
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUsernameAndPassword(String username, String password) {
        User user=null;
        try {
            String sql="select * from user where username = ? and password = ? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);

        } catch (DataAccessException e) {
        }
        return user;
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //定义sql
            String sql="select * from user where username = ? ";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
        }
        return user;
    }

    /**
     * 保存用户信息
     * @param user
     */
    @Override
    public void save(User user) {
        //1.定义sql
        String sql = "insert into user(username,password) values(?,?)";
        //2.执行sql
       template.update(sql,user.getUsername(), user.getPassword());
    }
}
