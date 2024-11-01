package com.jackson;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

public class JsonValueClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", TypeEnumWithValue.TYPE1);
        val json = mapper.writeValueAsString(car);
        System.out.println(json);//{"color":"yellow","type":"Type A"}

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//JsonValueClass.Car(color=yellow, type=TYPE1)
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        private String color;
        private TypeEnumWithValue type;
    }

    public enum TypeEnumWithValue {
        TYPE1(1, "Type A"), TYPE2(2, "Type 2");

        private Integer id;
        private String name;

        TypeEnumWithValue(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }
    }
}
