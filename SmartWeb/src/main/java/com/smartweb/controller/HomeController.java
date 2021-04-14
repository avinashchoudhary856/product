package com.smartweb.controller;

import com.smartweb.beans.SaveUserOutput;
import com.smartweb.beans.UserInput;
import com.smartweb.beans.UserList;
import com.smartweb.beans.UserOutput;
import com.smartweb.common.UserType;
import com.smartweb.entities.User;
import com.smartweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController{
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello from spring MVC");
        return "home";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/fetchuser",method = RequestMethod.POST)
    public @ResponseBody  UserOutput fetchUser(@RequestBody UserInput userInput){

        String userName = userInput.getUserName();
        String password = userInput.getPassword();
        System.out.println("UserName : "+userName);
        System.out.println("Password : "+password);
        UserOutput userOutput = new UserOutput();
        userOutput.setUserName(userName);
        User user = userService.getUser(userInput);
        if(user != null){
            System.out.println("it is success and redirecting to success");
            if(user.getUserType() == UserType.ADMIN) {
                userOutput.setPage("admin");
            }
            else {
                userOutput.setPage("userdetails");
            }
            userOutput.setUser(user);
            return userOutput;
        }
        System.out.println("it is failed so redirecting to failed");
        userOutput.setPage("failed");
        return userOutput;
    }

    @RequestMapping(value="/userdetails")
    public ModelAndView userDetails(@RequestParam(name = "userName") String userName){
        ModelAndView modelAndView = new ModelAndView("userdetails");
        modelAndView.addObject("userName",userName);
        modelAndView.addObject("user",userService.getUser(userName));
        return modelAndView;
    }

    @RequestMapping(value = "/success")
    public ModelAndView success(){
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/failed")
    public ModelAndView failed(){
        return new ModelAndView("failed");
    }

    @RequestMapping(value="/admin")
    public ModelAndView admin(@RequestParam(name = "userName") String userName){
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("user",userService.getUser(userName));
        return modelAndView;
    }

    @RequestMapping(value = "/saveuser",method = RequestMethod.POST)
    public @ResponseBody SaveUserOutput saveUser(@RequestBody User user){
        boolean added = userService.setUser(user);
        SaveUserOutput saveUserOutput = new SaveUserOutput();
        if(added) {
            saveUserOutput.setStatus("success");
        }else{
            saveUserOutput.setStatus("unable to add user. Could be because " +
                    "username is duplicate. Please enter correct value");
        }
        return saveUserOutput;
    }

    @RequestMapping(value = "/list")
    public @ResponseBody ModelAndView listUser(){
        ModelAndView modelAndView = new ModelAndView("list");
        List<User> users = userService.list();
        modelAndView.addObject("users",users);
        return modelAndView;
    }

    @RequestMapping(value = "/listusers")
    public @ResponseBody UserList getUserList(){
        List<User> users = userService.list();
        UserList userList = new UserList();
        userList.setUsers(users);
        return userList;
    }

}