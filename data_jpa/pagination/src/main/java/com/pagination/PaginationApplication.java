package com.pagination;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class PaginationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaginationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(AddressService service) {
        return args -> {//todo check if pageable.unpaged()
            /*Pageable sortedByName =
  PageRequest.of(0, 3, Sort.by("name"));

Pageable sortedByPriceDesc =
  PageRequest.of(0, 3, Sort.by("price").descending());

Pageable sortedByPriceDescNameAsc =
  PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));*/
            System.out.println("---getPageAll---");
            val pageable = PageRequest.of(0, 3, Sort.by("id").ascending());
            service.getPageAll(pageable)
                    .forEach(address -> System.out.println(address.toString()));

            System.out.println("---searchAddressesByCity---");
            val cityPageable = PageRequest.of(0, 2, Sort.by("id").descending());
            service.searchAddressesByCity("London", cityPageable)
                    .forEach(address -> System.out.println(address.toString()));

            System.out.println("---findAddressByCityWithPagination---");
            service.findAddressByCityWithPagination(10, 0)
                    .forEach(address -> System.out.println(address.toString()));

            System.out.println("---findByCityWithPagination---");
            service.findByCityWithPagination("London", cityPageable)
                    .forEach(address -> System.out.println(address.toString()));
        };
    }
}
