package com.smartweb.services;

import com.smartweb.beans.UserInput;
import com.smartweb.common.UserType;
import com.smartweb.common.UserYear;
import com.smartweb.entities.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("userService")
public class UserServiceImpl implements UserService{
    private Set<User> userObjects;

    @PostConstruct
    public void populateUserObjects(){
        userObjects = new HashSet<User>();
        User avinash = new User();
        avinash.setUserName("avinash");
        avinash.setFullName("Avinash Choudhary");
        avinash.setPassword("pass1");
        avinash.setUserType(UserType.ADMIN);
        userObjects.add(avinash);

        User bhupesh = new User();
        bhupesh.setUserName("bhupesh");
        bhupesh.setFullName("Bhupesh Choudhary");
        bhupesh.setPassword("pass2");
        bhupesh.setUserType(UserType.TEACHER);
        userObjects.add(bhupesh);

        User vikas = new User();
        vikas.setUserName("vikas");
        vikas.setFullName("Vikas Kumar");
        vikas.setPassword("pass3");
        vikas.setUserType(UserType.STUDENT);
        vikas.setUserYear(UserYear.FIRST);
        userObjects.add(vikas);

    }

    @Override
    public User getUser(UserInput userInput){
        for(User user: userObjects){
            if(user.getUserName().equals(userInput.getUserName()) && user.getPassword().equals(userInput.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUser(String userName){
        for(User user: userObjects){
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean setUser(User user){
        return userObjects.add(user);
    }

    @Override
    public List<User> list(){
        List<User> list = new ArrayList<User>();
        for(User user : userObjects){
            list.add(user);
        }
        return list;
    }
}