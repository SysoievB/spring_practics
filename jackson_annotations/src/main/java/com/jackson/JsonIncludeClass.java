package com.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
/**
 * @JsonInclude to exclude properties with empty/null/default values.*/
public class JsonIncludeClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", null);
        val json = mapper.writeValueAsString(car);
        System.out.println(json);//{"color":"yellow"}

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//JsonIncludeClass.Car(color=yellow, type=null)
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
