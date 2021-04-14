package com.smartweb.entities;

import com.smartweb.common.UserType;
import com.smartweb.common.UserYear;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String fullName;
    private UserType userType;
    private UserYear userYear;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserYear getUserYear() {
        return userYear;
    }

    public void setUserYear(UserYear userYear) {
        this.userYear = userYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if(userName == null){
            return false;
        }
        User user = (User) o;
        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        if(userName == null){
            return 0;
        }
        return userName.hashCode();
    }
}
