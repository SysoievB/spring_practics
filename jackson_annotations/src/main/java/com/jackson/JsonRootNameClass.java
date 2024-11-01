package com.jackson;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.*;

public class JsonRootNameClass {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        Car car = new Car("yellow", "someType");
        String json = mapper.writeValueAsString(car);
        System.out.println(json);
/*
{
    "car": {
        "color": "yellow",
        "type": "someType"
    }
}
*/

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);
        //JsonRootNameClass.Car(color=yellow, type=someType)
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonRootName(value = "car")
    private static class Car {
        private String color;
        private String type;
    }
}
