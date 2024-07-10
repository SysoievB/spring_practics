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
        val user = new User("Vasia", "vasia@mail.com", 24);
        val employee = new Employee("Petia", "petia@mail.com", 23);

        val userFromDB = userRepo.save(user);
        val employeeFromDB =  employeeRepo.save(employee);

        userRepo.findByIdAndName(userFromDB.getId(), userFromDB.getName())
                .ifPresent(System.out::println);

        employeeRepo.findByIdAndName(employee.getId(), employeeFromDB.getName())
                .ifPresent(System.out::println);
        //User(id=1, name=Vasia, email=vasia@mail.com, age=24)
        //Employee(id=1, name=Petia, email=petia@mail.com, experience=23)
    }
}
