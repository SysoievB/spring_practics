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
/**
 * Understanding Offset with an Example
 * Consider a dataset with 100 items and a page size of 10 items per page.
 * If you want to fetch the first page, the offset is 0 (no items are skipped).
 * For the second page, the offset is 10 (the first 10 items are skipped), and so on.
 *
 * Calculating Offset
 * The offset is typically calculated using the following formula:
 * offset = page number × page size
 * For example:
 * Page number 0, page size 10: offset = 0 * 10 = 0
 * Page number 1, page size 10: offset = 1 * 10 = 10
 * Page number 2, page size 10: offset = 2 * 10 = 20
 * Using Offset in SQL Queries
 * In SQL, the offset is used in combination with the LIMIT clause to fetch a specific page of results.
 * Here's an example SQL query:
 *
 * SELECT * FROM address
 * ORDER BY id ASC
 * LIMIT 10 OFFSET 20;
 *
 * This query retrieves 10 items, starting from the 21st item (offset 20).
 * */
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
