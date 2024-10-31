package com.jackson;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

public class JsonAliasClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", "renault", "famous brand");
        val json = mapper.writeValueAsString(car);
        System.out.println(json);//{"color":"yellow","type":"renault","brandName":"famous brand"}
        val brandName = """
                {"color":"yellow","type":"renault","brandName":"famous brand"}
                """;

        val brand_name = """
                {"color":"yellow","type":"renault","brand_name":"famous brand"}
                """;

        val brandname = """
                {"color":"yellow","type":"renault","brand-name":"famous brand"}
                """;
        Car car1 = mapper.readValue(brandName, Car.class);//JsonAliasClass.Car(color=yellow, type=renault, brandName=famous brand)
        Car car2 = mapper.readValue(brandname, Car.class);//JsonAliasClass.Car(color=yellow, type=renault, brandName=famous brand)
        Car car3 = mapper.readValue(brand_name, Car.class);//JsonAliasClass.Car(color=yellow, type=renault, brandName=famous brand)
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3);

    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        private String color;
        private String type;
        @JsonAlias({"brandName", "brand_name", "brand-name"})
        private String brandName;
    }
}