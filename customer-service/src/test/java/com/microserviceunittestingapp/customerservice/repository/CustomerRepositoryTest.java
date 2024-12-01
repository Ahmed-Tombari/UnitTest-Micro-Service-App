package com.microserviceunittestingapp.customerservice.repository;

import com.microserviceunittestingapp.customerservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        System.out.println("-----------------------------------------------");
        customerRepository.save(Customer.builder()
                .firstName("Ahmed").lastName("Tombari").email("ahmed@gmail.com").build());
        customerRepository.save(Customer.builder()
                .firstName("Amira").lastName("Alia").email("amira@gmail.com").build());
        customerRepository.save(Customer.builder()
                .firstName("Nabil").lastName("Tombari").email("nabil@gmail.com").build());
        System.out.println("-----------------------------------------------");
    }

    @Test
    void shouldFindCustomersByFirstName(){
        List<Customer> expected = List.of(
                Customer.builder().firstName("Ahmed").lastName("Tombari").email("ahmed@gmail.com").build(),
                Customer.builder().firstName("Amira").lastName("Alia").email("amira@gmail.com").build()
        );
        List<Customer> result = customerRepository.findByFirstNameContainingIgnoreCase("m");
        assertThat(result.size()).isEqualTo(2);
        assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result);
    }
    @Test
    void shouldFindCustomersByEmail(){
        String givenEmail="ahmed@gmail.com";
        Customer expected=Customer.builder().firstName("Ahmed").lastName("Tombari").email("ahmed@gmail.com").build();
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isPresent();
        assertThat(expected).usingRecursiveComparison().ignoringFields("id").isEqualTo(result.get());
    }
    @Test
    void shouldNotFindCustomersByEmail(){
        String givenEmail="xyz@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isEmpty();
    }
}

