package com.smartweb.api;

import com.smartweb.SmartWebApplication;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = SmartWebApplication.class)
@WebAppConfiguration
public class BaseTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                  .build();
    }

    protected ResultActions callApi(String apiName, HttpMethod method,String input) throws Exception{
        String url = apiName;
        MockHttpServletRequestBuilder builder = null;
        if(method == HttpMethod.POST){
            builder = MockMvcRequestBuilders.post(apiName);
            builder.contentType(MediaType.APPLICATION_JSON);
            builder.content(input);
        }else{
            builder = MockMvcRequestBuilders.get(apiName);
        }
        builder.accept(MediaType.APPLICATION_JSON);
        return mockMvc.perform(builder);
    }

    protected ResultActions callApi(String apiName, HttpMethod method) throws Exception {
        return callApi(apiName,method,null);
    }
}
