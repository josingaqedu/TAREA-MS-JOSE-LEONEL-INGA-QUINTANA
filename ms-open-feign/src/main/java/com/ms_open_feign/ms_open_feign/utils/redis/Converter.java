package com.ms_open_feign.ms_open_feign.utils.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String convertToString(T object){
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Error to convert object to string: " + e.getMessage());
        }
    }

    public static <T> T convertToObject(String json, Class<T> objectClass){
        try {
            return OBJECT_MAPPER.readValue(json, objectClass);
        }catch (JsonProcessingException e){
            throw new RuntimeException("Error to convert string to object: " + e.getMessage());
        }
    }
}
