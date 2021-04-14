package com.smartweb.services;

import com.smartweb.beans.UserInput;
import com.smartweb.entities.User;

import java.util.List;

public interface UserService {
    public User getUser(UserInput userInput);
    public User getUser(String userName);
    public boolean setUser(User user);
    public List<User> list();
}