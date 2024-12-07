package com.no_repository_bean;

import com.no_repository_bean.entity.Employee;
import com.no_repository_bean.entity.User;
import com.no_repository_bean.repo.EmployeeRepo;
import com.no_repository_bean.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class NoRepositoryBeanApplication implements CommandLineRunner {
    private final UserRepo userRepo;
    private final EmployeeRepo employeeRepo;

    public static void main(String[] args) {
        SpringApplication.run(NoRepositoryBeanApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        val user = new User("Vasia", "vasia@mail.com", LocalDate.now().minusYears(24));
        val employee = new Employee("Petia", "petia@mail.com", LocalDate.now().minusYears(30));

        val userFromDB = userRepo.save(user);
        val employeeFromDB =  employeeRepo.save(employee);

        System.out.println("----------findByIdAndName----------".toUpperCase());
        userRepo.findByIdAndName(userFromDB.getId(), userFromDB.getName())
                .ifPresent(System.out::println);

        employeeRepo.findByIdAndName(employee.getId(), employeeFromDB.getName())
                .ifPresent(System.out::println);

        // Create and save users
        val user1 = new User("John", "john@mail.com", LocalDate.now().minusYears(30));
        val user2 = new User("Jane", "jane@mail.com", LocalDate.now().minusYears(25));
        val user3 = new User("Alice", "alice@mail.com", LocalDate.now().minusYears(35));
        userRepo.saveAll(List.of(user1, user2, user3));

        // Create and save employees
        val emp1 = new Employee("Mark", "mark@mail.com", LocalDate.now().minusYears(40));
        val emp2 = new Employee("Lucy", "lucy@mail.com", LocalDate.now().minusYears(29));
        val emp3 = new Employee("Paul", "paul@mail.com", LocalDate.now().minusYears(31));
        employeeRepo.saveAll(List.of(emp1, emp2, emp3));

        // Define parameters for the query
        List<String> names = List.of("John", "Jane", "Lucy", "Mark", "Paul");
        LocalDate startDateRange = LocalDate.now().minusYears(40); // Start range: 33 years ago
        LocalDate endDateRange = LocalDate.now().minusYears(27);  // End range: 27 years ago

        System.out.println("----------findAllByNameInAndBirthdateBetweenIncluded----------".toUpperCase());
        // Fetch users by the criteria
        List<User> filteredUsers = userRepo.findAllByNameInAndBirthdateBetweenIncluded(names, startDateRange, endDateRange);
        System.out.println("Filtered Users: " + filteredUsers);

        // Fetch employees by the criteria
        List<Employee> filteredEmployees = employeeRepo.findAllByNameInAndBirthdateBetweenIncluded(names, startDateRange, endDateRange);
        System.out.println("Filtered Employees: " + filteredEmployees);
    }
}
