package com.smartweb.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartweb.beans.SaveUserOutput;
import com.smartweb.beans.UserInput;
import com.smartweb.beans.UserList;
import com.smartweb.beans.UserOutput;
import com.smartweb.common.UserType;
import com.smartweb.common.UserYear;
import com.smartweb.entities.User;
import com.smartweb.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


@RunWith(SpringJUnit4ClassRunner.class)
public class TestApi extends BaseTest{

    @Autowired
    UserService userService;

    @Before
    public void setup(){
        super.setup();
    }

    @Test
    public void testSaveUserForSuccess() throws Exception{
        String apiName = "/saveuser";
        User user = new User();
        user.setUserType(UserType.STUDENT);
        user.setFullName("Vaibhav Upadhyay");
        user.setUserName("vaibhav");
        user.setUserYear(UserYear.THIRD);
        user.setPassword("abc");
        ObjectMapper mapper = new ObjectMapper();
        String input = mapper.writeValueAsString(user);
        ResultActions resultActions = callApi(apiName, HttpMethod.POST,input);
        MvcResult result = resultActions.andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        SaveUserOutput saveUserOutput = mapper.readValue(response, SaveUserOutput.class);
        Assert.assertEquals("it should be success","success",saveUserOutput.getStatus());
    }

    @Test
    public void testSaveUserForSuccess2() throws Exception {
        String apiName = "/saveuser";
        User user = new User();
        user.setUserType(UserType.STUDENT);
        user.setFullName("Vaibhav Sharma");
        user.setUserName("vaibhav");
        user.setUserYear(UserYear.THIRD);
        user.setPassword("bcv");
        ObjectMapper mapper = new ObjectMapper();
        String input = mapper.writeValueAsString(user);
        ResultActions resultActions = callApi(apiName, HttpMethod.POST,input);
        MvcResult result = resultActions.andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        SaveUserOutput saveUserOutput = mapper.readValue(response, SaveUserOutput.class);
        Assert.assertEquals("it should be failed","unable to add user. Could be because " +
                "username is duplicate. Please enter correct value",saveUserOutput.getStatus());
    }

    @Test
    public void testFetchUserForSuccess() throws Exception {
        String apiName = "/fetchuser";
        UserInput userInput = new UserInput();
        userInput.setUserName("vaibhav");
        userInput.setPassword("bbb");
        ObjectMapper mapper = new ObjectMapper();
        String input = mapper.writeValueAsString(userInput);
        ResultActions resultActions = callApi(apiName, HttpMethod.POST,input);
        MvcResult result = resultActions.andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        UserOutput userOutput = mapper.readValue(response, UserOutput.class);
        Assert.assertEquals("Hello Vaibhav","vaibhav",userOutput.getUserName());
    }

    @Test
    public void testForUserService() throws Exception {
        String apiName = "/listusers";
        ResultActions resultActions = callApi(apiName, HttpMethod.GET);
        MvcResult result = resultActions.andReturn();
        String response = result.getResponse().getContentAsString();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        UserList userList = mapper.readValue(response, UserList.class);
        Assert.assertEquals(userList.getUsers(),userService.list());
    }

}