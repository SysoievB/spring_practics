package com.pagination;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootApplication
@AllArgsConstructor
public class PaginationApplication {
    private final AddressService service;

    public static void main(String[] args) {
        SpringApplication.run(PaginationApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp() {
        return args -> {
            getPageable();

            getUnpaged(null);

            System.out.println("---getPageAll---");
            val pageable = PageRequest.of(0, 3,
                    Sort.by("id").ascending()
                            .and(Sort.by("city").descending())
            );
            service.getPageAll(pageable)
                    .forEach(address -> System.out.println(address.toString()));

            System.out.println("---searchAddressesByCity---");
            val cityPageable = PageRequest.of(0, 2, Sort.by("id").descending());
            service.searchAddressesByCity("London", cityPageable)
                    .forEach(address -> System.out.println(address.toString()));

            System.out.println("---findAddressByCityWithPagination---");
            service.findAddressByCityWithPagination("Los Angeles", 4, 0)
                    .forEach(address -> System.out.println(address.toString()));

            System.out.println("---findByCityWithPagination---");
            service.findByCityWithPagination("London", cityPageable)
                    .forEach(address -> System.out.println(address.toString()));
        };
    }

    void getPageable() {
        System.out.println("---getPageable---");
        val pageable = PageRequest.of(0, 3, Sort.by("id").ascending());

        // Get the first page
        service.getPageAll(pageable)
                .getContent()
                .forEach(System.out::println);

        // Check if there is a previous page
        if (pageable.hasPrevious()) {
            service.getPageAll(pageable.previousOrFirst())
                    .getContent()
                    .forEach(System.out::println);
        } else {
            System.out.println("No previous page available.");
        }

        // Get the next page
        service.getPageAll(pageable.next())
                .getContent()
                .forEach(System.out::println);

        // Get the first page again
        service.getPageAll(pageable.first())
                .getContent()
                .forEach(System.out::println);

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        long offset = pageable.getOffset();
        val sort = pageable.getSort();

        System.out.println("Page Number: " + pageNumber);
        System.out.println("Page Size: " + pageSize);
        System.out.println("Offset: " + offset);
        System.out.println("Sort: " + sort);
    }

    void getUnpaged(@Nullable Pageable pageable) {
        System.out.println("---getUnpaged nullable---");
        service.findByCityWithPagination("London", pageable)
                .forEach(address -> System.out.println(address.toString()));
    }
}
