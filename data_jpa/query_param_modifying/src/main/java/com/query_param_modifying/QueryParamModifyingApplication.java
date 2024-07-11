package com.query_param_modifying;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class QueryParamModifyingApplication implements CommandLineRunner {
    private final UserRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(QueryParamModifyingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repo.saveAll(List.of(
                new User("Alice", "King", "alice@example.com", 25),
                new User("Bob", "Jobs", "bob@example.com", 30),
                new User("Bob", "Jobs", "bob@example.com", 32),
                new User("Bob", "Jobs", "bob@example.com", 17),
                new User("Charlie", "Caprio", "charlie@example.com", 22),
                new User("David", "King", "david@example.com", 28),
                new User("Bob", "Miocic", "bobby@example.com", 24),
                new User("Frank", "Ferguson", "frank@example.com", 35),
                new User("Grace", "Shevchenko", "grace@example.com", 27),
                new User("Hank", "Mir", "hank@example.com", 32),
                new User("Ivy", "Bridge", "ivy@example.com", 29),
                new User("Jack", "Cormier", "jack@example.com", 26)
        ));

        repo.findYoungestUser()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Empty repo!!!")
                );

        repo.findByNameAndSurname("Bob", "Jobs")
                .forEach(System.out::println);

        repo.findAllUsersWithPagination(Pageable.ofSize(4));

        // Define the page size
        Pageable pageable = PageRequest.of(0, 4);  // Page number 0 (first page) and 4 items per page

        // Retrieve paginated users
        Page<User> page = repo.findAllUsersWithPagination(pageable);

        // Print users
        System.out.println("Page " + (page.getNumber() + 1) + " of " + page.getTotalPages());
        page.getContent().forEach(user -> System.out.println("User: " + user));

        // If you want to fetch and print more pages, you can loop through them
        for (int i = 1; i < page.getTotalPages(); i++) {
            pageable = PageRequest.of(i, 4);  // Next page
            page = repo.findAllUsersWithPagination(pageable);
            System.out.println("Page " + (page.getNumber() + 1) + " of " + page.getTotalPages());
            page.getContent().forEach(user -> System.out.println("User: " + user));
        }
        System.out.println("---------------END OF PAGINATION-------------");

        repo.findUserByNameList(Set.of("Alice", "Frank"))
                .forEach(System.out::println);

        repo.updateUserSetSurnameForName("Updated", "Alice");
        repo.findByNameAndSurname("Alice", "Updated")
                .forEach(System.out::println);

        repo.insertUser("Name", "Ser", "some@email.com", 321);
    }
}
