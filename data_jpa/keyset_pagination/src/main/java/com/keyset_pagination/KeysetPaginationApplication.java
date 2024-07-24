package com.keyset_pagination;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeysetPaginationApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeysetPaginationApplication.class, args);
	}

	@Bean
	CommandLineRunner startUp(PostService service) {
		return args -> {
			val cursorBasedPageable = new CursorBasedPageable(3, null, null);
			val specification = new PageSpecification<Post>(
					"title",
					cursorBasedPageable
			);
			val postPage = service.findPage(specification, cursorBasedPageable);
			postPage.content().forEach(System.out::println);
			//returns first keyset sorted by title
			//Post(id=2, title=Exploring the Universe, shortDescription=A fascinating journey into outer space., longDescription=Embark on a cosmic adventure as we delve into the mysteries of the universe. From the birth of stars to the search for extraterrestrial life., createdBy=Jane Smith, createdAt=2023-05-20T06:00, publishedAt=2023-05-20T12:15:06)
			//Post(id=7, title=Financial Freedom: Your Path to Wealth, shortDescription=Unlock the secrets to financial independence., longDescription=Discover proven strategies for managing your finances, building wealth, and achieving financial freedom., createdBy=Christopher Anderson, createdAt=2023-02-20T08:00:01, publishedAt=2023-02-25T10:00)
			//Post(id=3, title=Healthy Living: A Balanced Approach, shortDescription=Achieve optimal well-being with this holistic guide., longDescription=Discover the secrets to living a healthy and balanced life., createdBy=David Johnson, createdAt=2023-05-19T15:39, publishedAt=2023-05-19T21:00)
		};
	}
}
