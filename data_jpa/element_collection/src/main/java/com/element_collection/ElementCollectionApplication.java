package com.element_collection;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class ElementCollectionApplication implements CommandLineRunner {
	private final HouseRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(ElementCollectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		val house = new House(
				"Berlin",
				List.of("green", "red"),
				Set.of("big", "small"),
				Map.of(
						"12m2", "living room",
						"10m2", "bedroom"
				)
		);
		repo.save(house);
		repo.findById(house.getId()).ifPresent(System.out::println);
		//House(id=1, city=Berlin, doors=[green, red], windows=[small, big], squareMetersAndRoomNames={12m2=living room, 10m2=bedroom})
	}
}
