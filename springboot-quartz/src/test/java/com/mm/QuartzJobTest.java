package com.mm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mm.modules.quartz.entity.QuartzJob;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzJobTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mm;

    ObjectMapper om = new ObjectMapper();

    @Before
    public void before() {
        mm = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void add() throws Exception {
        QuartzJob job = new QuartzJob();
        job.setJobName("有参定时任务");
        job.setBeanName("testTask");
        job.setMethodName("run1");
        job.setCronExpression("0/10 * * * * ?");
        job.setParams("这是一个有参定时问题");
        job.setRemark("这是一个有参定时问题");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/quartzJob/save").contentType(MediaType.APPLICATION_JSON);
        builder.content(om.writeValueAsString(job));
        MvcResult mr = mm.perform(builder).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
    }

    @Test
    public void list() throws Exception {
        String url = "/quartzJob/list?page=1&limit=10&beanName=test";
        MvcResult mr = mm.perform(MockMvcRequestBuilders.get(url)).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
    }

    @Test
    public void info() throws Exception {
        String url = "/quartzJob/info/1";
        MvcResult mr = mm.perform(MockMvcRequestBuilders.get(url)).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
    }

    @Test
    public void update() throws Exception {
        QuartzJob job = new QuartzJob();
        job.setId(1L);
        job.setCronExpression("0/30 * * * * ?");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/quartzJob/update").contentType(MediaType.APPLICATION_JSON);
        builder.content(om.writeValueAsString(job));
        MvcResult mr = mm.perform(builder).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
    }

    @Test
    public void run() throws Exception {
        Long[] jobIds = new Long[]{1L};
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/quartzJob/run").contentType(MediaType.APPLICATION_JSON);
        builder.content(om.writeValueAsString(jobIds));
        MvcResult mr = mm.perform(builder).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
    }

    @Test
    public void pauseAndResume() throws Exception {
        Long[] jobIds = new Long[]{1L};
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/quartzJob/pause").contentType(MediaType.APPLICATION_JSON);
        builder.content(om.writeValueAsString(jobIds));
        MvcResult mr = mm.perform(builder).andReturn();
        System.out.println(mr.getResponse().getStatus());
        System.out.println(mr.getResponse().getContentAsString());
        System.out.println("=======================================");
        Thread.sleep(30000);
        MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders.post("/quartzJob/resume").contentType(MediaType.APPLICATION_JSON);
        builder2.content(om.writeValueAsString(jobIds));
        MvcResult mr2 = mm.perform(builder2).andReturn();
        System.out.println(mr2.getResponse().getStatus());
        System.out.println(mr2.getResponse().getContentAsString());
        Thread.sleep(60000);
    }
}
