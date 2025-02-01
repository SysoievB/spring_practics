package com.one_to_one_primarykeyjoincolumn;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class OneToOnePrimarykeyjoincolumnApplication {
	private final AddressRepo addressRepository;
	private final UserRepo userRepository;

	public static void main(String[] args) {
		SpringApplication.run(OneToOnePrimarykeyjoincolumnApplication.class, args);
	}

	@Bean
	CommandLineRunner startUp() {
		return args -> {
			val user = new User("Vasia");
			val address = new Address("NY", "Main street");
			user.setAddress(address);
			address.setUser(user);

			userRepository.save(user);
			userRepository.findAll()
					.forEach(System.out::println);
			addressRepository.findAll()
					.forEach(System.out::println);
		};
	}
}
