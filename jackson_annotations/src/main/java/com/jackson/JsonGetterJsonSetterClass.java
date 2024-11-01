package com.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

public class JsonGetterJsonSetterClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("blue", "Toyota");
        val json = mapper.writeValueAsString(car);
        System.out.println(json);
/*{
    "type": "Toyota",
    "color": "blue"
}*/

        Car car1 = mapper.readValue(json, Car.class);
        System.out.println(car1);
//JsonGetterJsonSetterClass.Car(color=blue, type=Toyota)
    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        @Getter
        private String color;
        private String type;

        @JsonGetter
        public String getType() {
            return type;
        }

        @JsonSetter("color")
        public void setColor(String color) {
            this.color = color;
        }
    }
}
