package com.mm.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动插入新增修改时间
 *
 * @author lwl
 */
@Component
public class MybatisPlusHandler implements MetaObjectHandler {

    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        if (metaObject.hasGetter(CREATED_AT)) {
            metaObject.setValue(CREATED_AT, now);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        if (metaObject.hasGetter(CREATED_AT)) {
            metaObject.setValue(CREATED_AT, now);
        }
        if (metaObject.hasGetter(UPDATED_AT)) {
            metaObject.setValue(UPDATED_AT, now);
        }
    }
}
