package com.mm.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mm.test.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * 订单
 *
 * @author lwl
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
