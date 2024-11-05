package com.jackson.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

class WithLombokCustomBuilder {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car.CarBuilder()
                .color("yellow")
                .type("renault")
                .engine("V6")
                .createCar();

        val json = mapper.writeValueAsString(car);//{"color":"yellow","type":"renault","engine":"V6"}
        System.out.println(json);

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);//WithLombokCustomBuilder.Car(color=yellow, type=renault, engine=V6)

    }

    @ToString
    @Getter
    @Builder(builderClassName = "Builder")
    @JsonDeserialize(builder = Car.CarBuilder.class)
    @AllArgsConstructor
    private static class Car {
        private final String color;
        private final String type;
        private final String engine;

        @JsonPOJOBuilder(buildMethodName = "createCar", withPrefix = "")
        static class CarBuilder {
            private String color;
            private String type;
            private String engine;

            CarBuilder color(String colorB) {
                this.color = colorB;
                return this;
            }

            CarBuilder type(String typeB) {
                this.type = typeB;
                return this;
            }

            CarBuilder engine(String engineB) {
                this.engine = engineB;
                return this;
            }

            Car createCar() {
                return new Car(color, type, engine);
            }
        }
    }
}
