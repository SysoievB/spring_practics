package com.spring_transactions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTransactionsApplication.class, args);
	}

	@Bean
	CommandLineRunner startUp(final UserService userService) {
		return args -> {
			//userService.createUserWithOrder();//Order creation failed: Order amount exceeds limit!
			//userService.supportsPropagation();//sets entities to both tables
			//userService.notSupportedPropagation();//exception thrown java.lang.RuntimeException: Order amount too high!
			//userService.mandatoryPropagation();//sets entities to both tables

			//userService.nestedPropagation();
			//Hibernate: insert into users (email,name) values (?,?)
			//Nested transaction failed: JpaDialect does not support savepoints - check your JPA provider's capabilities

			//userService.neverPropagation();//Hibernate: insert into orders (amount,product) values (?,?)

		};
	}
}
