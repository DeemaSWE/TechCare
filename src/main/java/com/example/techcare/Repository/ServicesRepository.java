package com.example.techcare.Repository;

import com.example.techcare.Model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {

    Services findServicesById(Integer id);

    List<Services> findServicesByType(String type);

    List<Services> findServicesByHours(Integer hours);

    List<Services> findServicesByPriceBetween(Double min, Double max);
}
