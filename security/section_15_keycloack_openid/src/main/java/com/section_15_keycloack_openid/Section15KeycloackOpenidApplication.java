package com.section_15_keycloack_openid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Section15KeycloackOpenidApplication {

	/**
	 * Launch Docker first
	 * docker run -p 8080:8080 -e KEYCLOAK_ADMIN=<your_username> -e KEYCLOAK_ADMIN_PASSWORD=<your_password> quay.io/keycloak/keycloak:25.0.1 start-dev
	 * */

	public static void main(String[] args) {
		SpringApplication.run(Section15KeycloackOpenidApplication.class, args);
	}

}
