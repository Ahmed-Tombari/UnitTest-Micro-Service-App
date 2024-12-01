package com.microserviceunittestingapp.customerservice;

import com.microserviceunittestingapp.customerservice.entities.Customer;
import com.microserviceunittestingapp.customerservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		log.info("================= Initialization ================");
		return args -> {
			List<Customer> customers = List.of(
					Customer.builder()
							.firstName("Ahmed").lastName("Tombari").email("ahmed@gmail.com").build(),
					Customer.builder()
							.firstName("Amira").lastName("Alia").email("amira@gmail.com").build(),
					Customer.builder()
							.firstName("Nabil").lastName("Tombari").email("nabil@gmail.com").build()
			);
			customerRepository.saveAll(customers);
		};
	}
}
