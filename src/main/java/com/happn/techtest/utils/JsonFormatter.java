package com.happn.techtest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

public class JsonFormatter {

    private static JsonFormatter INSTANCE;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonFormatter() {}

    public static JsonFormatter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JsonFormatter();
        }
        return INSTANCE;
    }

    public static String simpleKeyValueMap(String value, int numberOfPOIs) {
        try {
            return MAPPER.writeValueAsString(Collections.singletonMap(value, numberOfPOIs));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unexpected exception when returning JSON value");
        }
    }
}
