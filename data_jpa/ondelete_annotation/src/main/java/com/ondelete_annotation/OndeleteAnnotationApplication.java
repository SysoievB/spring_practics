package com.ondelete_annotation;

import com.ondelete_annotation.entity.Door;
import com.ondelete_annotation.entity.House;
import com.ondelete_annotation.repo.DoorRepo;
import com.ondelete_annotation.repo.HouseRepo;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class OndeleteAnnotationApplication implements CommandLineRunner {
    private final DoorRepo doorRepo;
    private final HouseRepo houseRepo;

    public static void main(String[] args) {
        SpringApplication.run(OndeleteAnnotationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Creation
        val house = new House("grey");
        val door = new Door("white");

        doorRepo.save(door);
        val doorFromDb = doorRepo.findById(door.getId()).get();

        house.setDoor(doorFromDb);
        houseRepo.save(house);

        // Deletion of the door
        doorRepo.delete(doorFromDb);

        // Verify if the house is also deleted
        val houseFromDb = houseRepo.findById(house.getId());
        if (houseFromDb.isPresent()) {
            System.out.println("House is still present: " + houseFromDb.get());
        } else {
            System.out.println("House is deleted");//this will be invoked
        }
    }
}
