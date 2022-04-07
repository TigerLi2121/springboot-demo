package com.mm.test.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mm.test.entity.Order;
import com.mm.test.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lwl
 * @since 2022-04-06
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test/order")
public class OrderController {

    final IOrderService iOrderService;

    @RequestMapping("/save")
    public String save() {
        Order order = Order.builder().orderId(2L).orderStatus(1).build();
        iOrderService.save(order);
        return "ok";
    }

    @RequestMapping("/get")
    public Order get(Long orderId) {
        Order order = iOrderService.getOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, orderId), false);
        return order;
    }
}
