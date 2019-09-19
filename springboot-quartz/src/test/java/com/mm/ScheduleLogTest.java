package com.mm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleLogTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mm;

    @Before
    public void before() {
        mm = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void list() throws Exception {
        String url = "/quartzLog/list?page=1&limit=10&jobId=";
        MvcResult mr = mm.perform(MockMvcRequestBuilders.get(url)).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
    }

    @Test
    public void info() throws Exception {
        String url = "/quartzLog/info/1";
        MvcResult mr = mm.perform(MockMvcRequestBuilders.get(url)).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
    }

}
