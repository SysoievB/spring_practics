package com.jackson.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import lombok.val;

class WithLombokClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = Car.builder()
                .color("yellow")
                .type("renault")
                .engine("V6")
                .build();
        val json = mapper.writeValueAsString(car);//{"color":"yellow","type":"renault","engine":"V6"}
        System.out.println(json);

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//WithLombokClass.Car(color=yellow, type=renault, engine=V6)

    }

    @ToString
    @Data
    @Builder//or @SupeBuilder required to be present
    @Jacksonized//experimental feature -> without this annotation object cannot be deserialized
    private static class Car {
        private String color;
        private String type;
        private String engine;
    }
}