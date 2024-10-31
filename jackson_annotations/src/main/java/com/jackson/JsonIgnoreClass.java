package com.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

public class JsonIgnoreClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", "renault", "ignored");
        val json = mapper.writeValueAsString(car);//{"color":"yellow","type":"renault"}
        System.out.println(json);

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//JsonIgnoreClass.Car(color=yellow, type=renault, ignoredField=null)

    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        private String color;
        private String type;
        @JsonIgnore
        private String ignoredField;
    }
}
