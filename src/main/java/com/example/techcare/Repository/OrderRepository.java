package com.example.techcare.Repository;

import com.example.techcare.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    Orders findOrdersById(Integer orderId);

    List<Orders> findOrdersByCustomerId(Integer customerId);

    List<Orders> findOrdersByStatus(String status);

}
