package com.entity_graph;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EntityGraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntityGraphApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(CharacteristicsRepository characteristicsRepository, ItemRepository itemRepository) {
        return args -> {
            val item = new Item("Some item");

            val characteristic = new Characteristic("Some type");
            characteristic.setItem(item);
            item.getCharacteristics().add(characteristic);

            itemRepository.save(item);

            System.out.println(characteristicsRepository.findByType("Some type"));
            //Characteristic(id=1, type=Some type, item=com.entity_graph.Item@166e9c4a)
        };
    }
}
