package com.example.techcare.Repository;

import com.example.techcare.Model.Services;
import com.example.techcare.Repository.ServicesRepository;
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
public class ServicesRepositoryTest {
    @Autowired
    ServicesRepository servicesRepository;



    Services services1;

    // 3- Ahmad
    @Test
    public void findServicesById(){
        servicesRepository.save(services1);
        Services services=servicesRepository.findServicesById(services1.getId());
        Assertions.assertThat(services).isEqualTo(services1);
    }
}
