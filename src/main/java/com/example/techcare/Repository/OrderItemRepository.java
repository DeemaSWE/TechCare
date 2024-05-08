package com.example.techcare.Repository;

import com.example.techcare.Model.OrderItem;
import com.example.techcare.Model.Orders;
import com.example.techcare.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    OrderItem findOrderItemByProductAndOrder(Product product, Orders order);
}
