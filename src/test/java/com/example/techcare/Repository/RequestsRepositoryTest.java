package com.example.techcare.Repository;

import com.example.techcare.Model.Requests;
import com.example.techcare.Repository.RequestsRepository;
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
public class RequestsRepositoryTest {
    @Autowired
    RequestsRepository requestsRepository;

    Requests requests1;

    // 2- Ahmad
    @Test
    public void findRequestsById(){
        requestsRepository.save(requests1);
        Requests requests=requestsRepository.findRequestsById(requests1.getId());
        Assertions.assertThat(requests).isEqualTo(requests1);
    }
}
