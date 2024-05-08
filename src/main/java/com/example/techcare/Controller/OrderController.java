package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.DTO.OrderItemDTO;
import com.example.techcare.Model.Orders;
import com.example.techcare.Model.User;
import com.example.techcare.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 12- Deema: Admin get all orders
    @GetMapping("/get-all")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    // 13- Deema: Admin update an order
    @PutMapping("/update/{orderId}")
    public ResponseEntity updateOrder(@PathVariable Integer orderId, @RequestBody @Valid Orders updatedOrder){
        orderService.updateOrder(orderId, updatedOrder);
        return ResponseEntity.status(200).body(new ApiResponse("Order updated successfully"));
    }

    // 14- Deema: Admin delete an order
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Integer orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(200).body(new ApiResponse("Order deleted successfully"));
    }

    // 15- Deema: Admin change order status (processing, shipped, delivered)
    @PutMapping("/update-status/{orderId}/{status}")
    public ResponseEntity updateOrderStatus(@PathVariable Integer orderId, @PathVariable String status){
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.status(200).body(new ApiResponse("Order status updated successfully"));
    }

    // 16- Deema: Customer make an order
    @PostMapping("/create")
    public ResponseEntity createOrder(@AuthenticationPrincipal User user, @RequestBody @Valid List<OrderItemDTO> orderProducts){
        orderService.createOrder(user.getId(), orderProducts);
        return ResponseEntity.status(200).body(new ApiResponse("Order created successfully"));
    }

    // 17- Deema: Customer get their orders
    @GetMapping("/get-my-orders")
    public ResponseEntity getCustomerOrders(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(orderService.getCustomerOrders(user.getId()));
    }

    // 18- Sara: Customer get order by status
    @GetMapping("/get-order-by-status/{status}")
    public ResponseEntity getCustomerOrderByStatus(@AuthenticationPrincipal User user,@PathVariable String status){
        return ResponseEntity.status(200).body(orderService.getOrdersByStatus(user.getId(), status));
    }

    // 19- Sara: Customer get order by date
    @GetMapping("/get-order-by-date/{date}")
    public ResponseEntity getCustomerOrderByDate(@AuthenticationPrincipal User user, @PathVariable LocalDate date){
        return ResponseEntity.status(200).body(orderService.getOrderByDate(user.getId(), date));
    }

}
