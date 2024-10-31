package com.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import lombok.val;

public class WithoutNoArgConstructorClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car1("yellow", "renault");
        val json = mapper.writeValueAsString(car);//{"color":"yellow","type":"renault"}
        System.out.println(json);

        Car1 car1 = mapper.readValue(json, Car1.class);
        System.out.println(car1);//WithoutNoArgConstructorClass.Car1(color=yellow, type=renault)

    }

    @ToString
    @Getter
    private static class Car1 {
        private String color;
        private String type;

        @JsonCreator
        Car1(@JsonProperty("color") String color, @JsonProperty("type") String type) {
            this.color = color;
            this.type = type;
        }
    }
}
