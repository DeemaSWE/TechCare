package com.example.techcare.Repository;

import com.example.techcare.Model.Customer;
import com.example.techcare.Model.Requests;
import com.example.techcare.Model.Technician;
import com.example.techcare.Model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestsRepository extends JpaRepository<Requests,Integer> {
    List<Requests> findRequestsByCustomer(Customer customer);
    Requests findRequestsById(Integer requestId);
    List<Requests> findRequestsByCustomerAndStatus(Customer customer,String status);
    List<Requests> findRequestsByTechnicianAndStatus(Technician technician, String status);

    List<Requests> findRequestsByTechnician(Technician technician);

    List<Requests> findRequestsByTrainer(Trainer trainer);

    Requests findRequestsByCustomerAndId(Customer customer, Integer requestId);

    Requests findRequestsByTrainerAndId(Trainer trainer, Integer requestId);

    Requests findRequestsByCustomerAndTechnicianAndId(Customer customer, Technician tech,Integer requestId);



}
