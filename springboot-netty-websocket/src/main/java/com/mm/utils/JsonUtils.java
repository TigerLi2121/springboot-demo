package com.mm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String bean2Json(T bean) {
        try {
            return objectMapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            log.error("bean2Json e:", e);
        }
        return null;
    }

    public static <T> T json2Bean(String json, Class<T> beanClass) {
        try {
            return objectMapper.readValue(json, beanClass);
        } catch (IOException e) {
            log.error("json2Bean e:", e);
        }
        return null;
    }

    public static String map2Json(Map map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("map2Json e:", e);
        }
        return null;
    }

    public static Map json2Map(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            log.error("json2Map e:", e);
        }
        return null;
    }

    public static String list2Json(List list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.error("list2Json e:", e);
        }
        return null;
    }

    public static <T> List<T> json2List(String json, Class<T> beanClass) {
        try {
            return objectMapper.readValue(json, getCollectionType(List.class, beanClass));
        } catch (IOException e) {
            log.error("json2List e:", e);
        }
        return null;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
