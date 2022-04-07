package com.mm.test.service.impl;

import com.mm.test.entity.Order;
import com.mm.test.mapper.OrderMapper;
import com.mm.test.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lwl
 * @since 2022-04-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
