package com.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

public class JacksonPolymorphicTypeHandlingClass {
    public static void main(String[] args) throws JsonProcessingException {
        val dog = new Zoo.Dog("lacy", 1.0);
        val zoo = new Zoo(dog);

        val json = new ObjectMapper().writeValueAsString(zoo);
        System.out.println(json);
/*
{
    "animal": {
        "type": "dog",
        "name": "lacy",
        "barkVolume": 1.0
    }
}
*/
    }

    @AllArgsConstructor
    private static class Zoo {
        public Animal animal;

        @JsonTypeInfo(
                use = JsonTypeInfo.Id.NAME,
                include = JsonTypeInfo.As.PROPERTY,
                property = "type")
        @JsonSubTypes({
                @JsonSubTypes.Type(value = Dog.class, name = "dog"),
                @JsonSubTypes.Type(value = Cat.class, name = "cat")
        })
        @AllArgsConstructor
        public static class Animal {
            public String name;
        }

        @JsonTypeName("dog")
        public static class Dog extends Animal {
            public double barkVolume;

            public Dog(String name, double barkVolume) {
                super(name);
                this.barkVolume = barkVolume;
            }
        }

        @JsonTypeName("cat")
        public static class Cat extends Animal {
            boolean likesCream;
            public int lives;

            public Cat(String name, boolean likesCream, int lives) {
                super(name);
                this.likesCream = likesCream;
                this.lives = lives;
            }
        }
    }
}
