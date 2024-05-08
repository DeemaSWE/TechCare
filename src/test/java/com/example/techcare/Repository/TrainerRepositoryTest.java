package com.example.techcare.Repository;

import com.example.techcare.Model.Trainer;
import com.example.techcare.Repository.TrainerRepository;
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
public class TrainerRepositoryTest {
    @Autowired
    TrainerRepository trainerRepository;

    Trainer trainer1;
//    User user;
//
//    @BeforeEach
//    void setUp() {
//
//        user=new User(null,"Ahmed","12345","aaa",null,null,null);
//        trainer1 = new Trainer(null,"ahmed","Ahmed@gmail.com","0558636915",23,"male","Beginner",5,user,null);
//        trainer2 = new Trainer(null,"trainer2","trainer2@gmail.com","0558636916",26,"male","Beginner",0,user,null );
//    }


    // 5- Ahmad
    @Test
    public void findTrainerById(){
        trainerRepository.save(trainer1);
        Trainer trainer=trainerRepository.findTrainerById(trainer1.getId());
        Assertions.assertThat(trainer).isEqualTo(trainer1);
    }
}
