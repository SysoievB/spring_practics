package com.jackson.ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

public class JsonIgnoreTypeClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", "renault", new Driver("Vasia"));
        val json = mapper.writeValueAsString(car);//{"color":"yellow","type":"renault"}
        System.out.println(json);

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);
        //JsonIgnorePropertiesClass.Car(color=yellow, type=renault, driver=null)
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        private String color;
        private String type;
        private Driver driver;
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreType
    private static class Driver {
        private String name;
    }
}
