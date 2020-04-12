package com.happn.techtest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.happn.techtest.model.GridZone;

import java.util.Collections;
import java.util.List;

public class JsonFormatter {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonFormatter() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static String simpleKeyValueMap(String value, int numberOfPOIs) {
        try {
            return MAPPER.writeValueAsString(Collections.singletonMap(value, numberOfPOIs));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unexpected exception when returning JSON value");
        }
    }

    public static String listAsJson(List<GridZone> nDensestZone) {
        try {
            return MAPPER.writeValueAsString(nDensestZone);
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
