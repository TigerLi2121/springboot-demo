package com.mm;

import com.mm.service.AService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ServiceTest {

    @Autowired
    private AService aService;

    @Test
    public void aa() {
        System.out.println(aService.aa().hashCode());
        System.out.println(aService.aa().hashCode());
    }

}
