package com.mm.modules.job.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 测试
 */
@Slf4j
@Component
public class TestTask {

    public void run(){
        log.info("无参定时任务");
    }

    public void run1(String s){
        log.info("有参定时任务 s:{}", s);
    }
}
