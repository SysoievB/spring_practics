package com.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

public class JsonAnyGetterSetterClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        Map<String, String> yearAndNames = new HashMap<>() {{
            put("2020", "Alice");
            put("2021", "Bob");
        }};

        val car = new Car("blue", "Toyota", yearAndNames);
        val json = mapper.writeValueAsString(car);
        System.out.println(json);
//{
//    "color": "blue",
//    "type": "Toyota",
//    "2021": "Bob",
//    "2020": "Alice"
//}

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);
//JsonAnyGetterSetterClass.Car(color=blue, type=Toyota, yearAndNames={2021=Bob, 2020=Alice})
    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        public String color;
        public String type;
        private Map<String, String> yearAndNames;

        @JsonAnyGetter
        public Map<String, String> getYearAndNames() {
            return yearAndNames;
        }

        @JsonAnySetter//work only for strings
        public void setProperty(String key, String value) {
            if (yearAndNames == null) {
                yearAndNames = new HashMap<>(); // lazy initialization
            }
            yearAndNames.put(key, value);
        }
    }
}

