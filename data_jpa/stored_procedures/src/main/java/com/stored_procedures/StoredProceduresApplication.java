package com.stored_procedures;

import com.stored_procedures.named_procedure.User;
import com.stored_procedures.named_procedure.UserRepository;
import com.stored_procedures.named_procedure.UserService;
import com.stored_procedures.procedure.Car;
import com.stored_procedures.procedure.CarRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class StoredProceduresApplication implements CommandLineRunner {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(StoredProceduresApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        val cars = List.of(
                new Car("Toyota Camry", 2020),
                new Car("Honda Accord", 2019),
                new Car("Ford Mustang", 2021),
                new Car("Chevrolet Camaro", 2018),
                new Car("BMW 3 Series", 2020),
                new Car("Mercedes-Benz C-Clas", 2020),
                new Car("Ford Mustang", 2021),
                new Car("Tesla Model 3", 2020),
                new Car("Ford Mustang", 2019),
                new Car("Nissan Altima", 2018)
        );
        carRepository.saveAll(cars);

        //Hibernate: {call GET_TOTAL_CARS_BY_MODEL(?,?)}
        System.out.println(carRepository.getTotalCarsByModel("Ford Mustang"));//3
        //Hibernate: {call GET_TOTAL_CARS_BY_MODEL(?,?)}
        System.out.println(carRepository.GET_TOTAL_CARS_BY_MODEL("Ford Mustang"));//3
        //Hibernate: {call GET_TOTAL_CARS_BY_MODEL(?,?)}
        System.out.println(carRepository.getTotalCarsByModelProcedureName("Ford Mustang"));//3
        //Hibernate: {call GET_TOTAL_CARS_BY_MODEL(?,?)}
        System.out.println(carRepository.getTotalCarsByModelValue("Ford Mustang"));//3


        val users = List.of(
                new User("Petia", 20),
                new User("Vasia", 20),
                new User("Ivan", 32),
                new User("Alina", 45),
                new User("Olga", 33)
        );
        userRepository.saveAll(users);

        System.out.println(userService.findUsersByAge(20));//2
    }
}
