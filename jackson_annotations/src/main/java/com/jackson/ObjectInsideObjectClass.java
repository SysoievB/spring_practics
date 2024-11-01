package com.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

public class ObjectInsideObjectClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", "renault", new Driver("Vasia"));
        val json = mapper.writeValueAsString(car);
        System.out.println(json);//{"color":"yellow","type":"renault","driver":{"name":"Vasia"}}

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);
        //ObjectInsideObjectClass.Car(color=yellow, type=renault, driver=ObjectInsideObjectClass.Driver(name=Vasia))
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
    private static class Driver {
        private String name;
    }
}
