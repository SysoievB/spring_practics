package com.dto_projection;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class DtoProjectionApplication implements CommandLineRunner {
    private final AddressRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DtoProjectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.getAddressByState("CA")
                .forEach(addressView -> System.out.println(addressView.getZipCode()));//90001

        System.out.println(repository.findByState("CA"));
        //AddressDTO[state=CA, city=Los Angeles]
    }
}
