package com.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;

public class JsonIncludePropertiesClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", "renault", "ignored");
        val json = mapper.writeValueAsString(car);
        System.out.println(json);
//{"color":"yellow"}
        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//JsonPropertyOrderClass.Car(color=yellow, type=null, size=null)

    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIncludeProperties({"color"})
    private static class Car {
        public String color;
        public String type;
        public String size;
    }
}
