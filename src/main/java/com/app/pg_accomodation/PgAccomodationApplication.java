package com.app.pg_accomodation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.app.pg_accomodation.entity")
public class PgAccomodationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PgAccomodationApplication.class, args);
	}

}
