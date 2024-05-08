package com.example.techcare.Repository;

import com.example.techcare.Model.*;
import com.example.techcare.Repository.*;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TechnicianRepositoryTest {
    @Autowired
    TechnicianRepository technicianRepository;

    Technician technician1;

    // 4- Ahmad
    @Test
    public void findTechnicianById(){
        technicianRepository.save(technician1);
        Technician technician=technicianRepository.findTechnicianById(technician1.getId());
        Assertions.assertThat(technician).isEqualTo(technician1);
    }
}
