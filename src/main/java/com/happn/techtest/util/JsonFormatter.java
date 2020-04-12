package com.happn.techtest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.Collections;

public class JsonFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    private JsonFormatter() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static String simpleKeyValueMap(Object key, Object value) {
        try {
            return MAPPER.writeValueAsString(Collections.singletonMap(key, value));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unexpected exception when returning JSON value");
        }
    }

    public static String objectAsJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unexpected exception when returning JSON value");
        }
    }

    public static <T> T fromJson(String arg, Class<T> targetClass) {
        try {
            return MAPPER.readValue(arg, targetClass);
        } catch (JsonMappingException e) {
            throw new RuntimeException(("Unable to map json to expected class"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unexpected exception when mapping JSON input");
        }
    }
}
