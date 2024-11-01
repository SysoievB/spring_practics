package com.jackson.serialize;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class JsonRawValueClass {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car("yellow", "{\"brand\": \"renault\", \"model\": \"clio\"}");

        String json = mapper.writeValueAsString(car);
        System.out.println(json);
/*
* {
    "color": "yellow",
    "type": {
        "brand": "renault",
        "model": "clio"
    }
}*/
    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        public String color;

        @JsonRawValue
        public String type;
    }
}