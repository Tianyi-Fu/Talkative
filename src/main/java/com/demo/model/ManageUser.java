package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class ManageUser {

    private Long uid;
    private String username;
    private String userRole;
    private String password;
    private String createdAt;
    public ManageUser() {
    }
    public ManageUser(Long uid, String username, String password, String userRole, String createdAt) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.createdAt = createdAt;
    }

    public Long getUid() {
        return uid;
    }

    public void setUserId(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}