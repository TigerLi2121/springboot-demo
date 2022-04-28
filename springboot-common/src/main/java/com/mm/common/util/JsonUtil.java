package com.mm.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * json格式转换
 *
 * @author: shmily
 * @date: 2018/1/18 18:18
 **/
@Slf4j
public class JsonUtil {
    public static final ObjectMapper om = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> String toJson(T bean) {
        try {
            return om.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            log.error("bean2Json e:", e);
        }
        return null;
    }

    public static <T> T toBean(String json, Class<T> beanClass) {
        try {
            return om.readValue(json, beanClass);
        } catch (IOException e) {
            log.error("json2Bean e:", e);
        }
        return null;
    }

    public static String toJson(Map map) {
        try {
            return om.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("map2Json e:", e);
        }
        return null;
    }

    public static Map toMap(String json) {
        try {
            return om.readValue(json, Map.class);
        } catch (IOException e) {
            log.error("json2Map e:", e);
        }
        return null;
    }

    public static String toJson(List list) {
        try {
            return om.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.error("list2Json e:", e);
        }
        return null;
    }

    public static <T> List<T> toList(String json, Class<T> beanClass) {
        try {
            return om.readValue(json, getCollectionType(List.class, beanClass));
        } catch (IOException e) {
            log.error("json2List e:", e);
        }
        return null;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return om.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
