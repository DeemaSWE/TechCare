package com.example.techcare.Repository;

import com.example.techcare.Model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Integer> {
    Trainer findTrainerById(Integer id);
    List<Trainer> findByLevel(String level);
}
