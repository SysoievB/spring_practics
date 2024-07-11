package com.created_modified_by_transient;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@AllArgsConstructor
public class CreatedModifiedByTransientApplication implements CommandLineRunner {
	private final PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(CreatedModifiedByTransientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Person person = new Person();
		person.setBankAccount("12345");

		personRepository.save(person);

		val fetchedPerson = personRepository.findById(person.getId());
		fetchedPerson.ifPresent(p -> System.out.println("Created Person: " + p));

		// Update the person
		fetchedPerson.ifPresent(p -> {
			p.setBankAccount("67890");
			val updated = personRepository.save(p);
            System.out.println("Updated Person: " + updated);
		});
	}
}
//Created Person: Person(id=1, bankAccount=null, creator=null, modifier=null, createdAt=2024-07-11 06:18:17.021, modifiedAt=2024-07-11 06:18:17.021)
//Updated Person: Person(id=1, bankAccount=null, creator=null, modifier=null, createdAt=2024-07-11 06:18:17.021, modifiedAt=2024-07-11 06:18:17.021)
