package com.demo.mapper;

import com.demo.model.ManageUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {
    @Insert("Insert Into user(username, password) Values(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    void insert(ManageUser user);

    @Select("Select uid,username, password,user_role From user Where username=#{username}")
    ManageUser selectByUsername(String username);

    @Select("select * from user")
    List<ManageUser> findAll();

    //    @Update("UPDATE USER SET name=#{name}, password=#{password}, user_role=#{userRole} WHERE uid=#{uid}")
//    void update(ManageUser manageUser);
    @Delete("DELETE FROM user WHERE uid=#{uid}")
    void delete(Long uid);
}