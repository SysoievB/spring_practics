package com.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZonedDateTime;

public class JsonFormatClass {
    public static void main(String[] args) throws JsonProcessingException {
        val mapper = new ObjectMapper();
        val car = new Car("yellow", LocalDate.now(), YearMonth.now(), ZonedDateTime.now());
        val json = mapper
                .registerModule(new JavaTimeModule())
                .writeValueAsString(car);
        System.out.println(json);
/*
{
    "color": "yellow",
    "year": "02-11-2024",
    "month": "11-2024",
    "timestamp": "02-11-2024 06:34:29"
}
*/
    }

    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Car {
        public String color;
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd-MM-yyyy")
        public LocalDate year;
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "MM-yyyy")
        public YearMonth month;
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "dd-MM-yyyy hh:mm:ss")
        public ZonedDateTime timestamp;
    }
}
