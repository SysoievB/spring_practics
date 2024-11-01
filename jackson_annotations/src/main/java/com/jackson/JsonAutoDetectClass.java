package com.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;

public class JsonAutoDetectClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", "renault", "ignored");
        val json = mapper.writeValueAsString(car);
        System.out.println(json);
//{"color":"yellow","type":"renault","size":"ignored"}
        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//JsonAutoDetectClass.Car(color=yellow, type=renault, size=ignored)

    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class Car {
        private String color;
        private String type;
        private String size;
    }
}
