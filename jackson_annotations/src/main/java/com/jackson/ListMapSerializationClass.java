package com.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMapSerializationClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val names = List.of("Alice", "Bob", "Charlie");
        Map<Integer, String> yearAndNames = new HashMap<>() {{
            put(2020, "Alice");
            put(2021, "Bob");
        }};

        val car = new Car("blue", "Toyota", names, yearAndNames);
        val json = mapper.writeValueAsString(car);//{"color":"blue","type":"Toyota","names":["Alice","Bob","Charlie"],"yearAndNames":{"2020":"Alice","2021":"Bob"}}
        System.out.println(json);

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//ListMapSerializationClass.Car(color=blue, type=Toyota, names=[Alice, Bob, Charlie], yearAndNames={2020=Alice, 2021=Bob})

    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        private String color;
        private String type;
        private List<String> names;
        private Map<Integer, String> yearAndNames;
    }
}
