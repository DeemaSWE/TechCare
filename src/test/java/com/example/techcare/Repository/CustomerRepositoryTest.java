package com.example.techcare.Repository;

import com.example.techcare.Model.Customer;
import com.example.techcare.Repository.CustomerRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    Customer customer1;

    // 1- Ahmad
    @Test
    public void findCustomerById(){
        customerRepository.save(customer1);
        Customer customer=customerRepository.findCustomerById(customer1.getId());
        Assertions.assertThat(customer).isEqualTo(customer1);
    }
}