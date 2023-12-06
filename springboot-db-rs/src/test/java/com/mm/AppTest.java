package com.mm;

import com.mm.test.dao.OrderMapper;
import com.mm.test.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void save() {
        Order order = Order.builder().status(1).build();
        orderMapper.insert(order);

//        Order.builder().status(1).build().insert();
    }

}
