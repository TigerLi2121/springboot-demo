package com.mm;

import com.mm.common.jwt.JwtBean;
import com.mm.common.jwt.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * jwt
 * @author shmily
 * @date 2019/4/10
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtBean jwtBean;

    @Test
    public void createJwt(){
        log.info("token:{}", JwtHelper.getToken(123));
    }

    @Test
    public void parseJWT(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiI4NDNhNDc1ZDJkY2E0M2QwODdiYzRkNTJiMjYzMTRlMiIsIm5iZiI6MTU1NDkwOTg5OSwiaXNzIjoicXFiYyIsImV4cCI6MTU1NTA4MjY5OSwidXNlcklkIjoiMTIzIn0.Ozb0FyBXttwLluKNXJDmINBvatveh3mt4x_g6gjbhrk";
        log.info("userId:{}", JwtHelper.parseJWT(token, jwtBean.getBase64Secret()).get("userId").asString());
    }
}
