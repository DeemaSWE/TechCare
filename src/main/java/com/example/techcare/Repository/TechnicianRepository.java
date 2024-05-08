package com.example.techcare.Repository;

import com.example.techcare.Model.Category;
import com.example.techcare.Model.Services;
import com.example.techcare.Model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer>{
    Technician findTechnicianById(Integer id);

//    Technician findTechnicianByName(String name);

    List<Technician> findTechnicianByYearsOfExperience(Integer years);

    List<Technician> findTop10ByOrderByAvgRatingDesc();
    List<Technician> findTechnicianByAvgRating(Double rate);


}
