package com.example.techcare.Service;

import com.example.techcare.Api.ApiException;
import com.example.techcare.DTO.OrderItemDTO;
import com.example.techcare.Model.Customer;
import com.example.techcare.Model.OrderItem;
import com.example.techcare.Model.Orders;
import com.example.techcare.Model.Product;
import com.example.techcare.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductsRepository productRepository;

    // 12- Deema: Admin get all orders
    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    // 13- Deema: Admin update an order
    public void updateOrder(Integer orderId, Orders updatedOrder){
        Orders order = orderRepository.findOrdersById(orderId);

        if(order == null)
            throw new ApiException("Order not found");

        order.setTotalPrice(updatedOrder.getTotalPrice());
        order.setDate(updatedOrder.getDate());
        order.setStatus(updatedOrder.getStatus());

        orderRepository.save(order);
    }

    // 14- Deema: Admin delete an order
    public void deleteOrder(Integer orderId){
        Orders order = orderRepository.findOrdersById(orderId);

        if(order == null)
            throw new ApiException("Order not found");

        orderRepository.delete(order);
    }

    // 15- Deema: Admin change order status (processing, shipped, delivered)
    public void updateOrderStatus(Integer orderId, String status){
        Orders order = orderRepository.findOrdersById(orderId);

        if(order == null)
            throw new ApiException("Order not found");

        if(!status.equalsIgnoreCase("processing") && !status.equalsIgnoreCase("shipped") && !status.equalsIgnoreCase("delivered"))
            throw new ApiException("status should be processing, shipped, or delivered");

        order.setStatus(status);

        orderRepository.save(order);
    }

    // 16- Deema: Customer make an order
    public void createOrder(Integer userId, List<OrderItemDTO> orderItemList){
        Customer customer = customerRepository.findCustomerById(userId);

        double totalPrice = 0;

        for(OrderItemDTO orderItemDTO : orderItemList){
            // calculate total price
            Product product = productRepository.findProductById(orderItemDTO.getProductId());
            totalPrice += (product.getPrice() * orderItemDTO.getQuantity());

            // check if customer has enough balance
            if(customer.getBalance() < totalPrice)
                throw new ApiException("Insufficient balance");

            // check if product is out of stock
            if(product.getStock() == 0)
                throw new ApiException("Product with id " + product.getId() + " is out of stock");

            // check if product stock is enough for the order
            if(orderItemDTO.getQuantity() > product.getStock())
                throw new ApiException("Product with id " + product.getId() + " stock is not enough for the order");

        }


        Orders order = new Orders();
        order.setDate(LocalDate.now());
        order.setStatus("processing");
        order.setShippingAddress(customer.getAddress());
        order.setTotalPrice(totalPrice);
        order.setCustomer(customer);

        orderRepository.save(order);

        for(OrderItemDTO orderItemDTO : orderItemList){
            Product product = productRepository.findProductById(orderItemDTO.getProductId());

            // deduct product stock
            product.setStock(product.getStock() - orderItemDTO.getQuantity());
            productRepository.save(product);

            // create order item
            OrderItem orderProduct = new OrderItem(null, orderItemDTO.getQuantity(), order, product);
            orderItemRepository.save(orderProduct);

        }

        // deduct customer balance
        customer.setBalance(customer.getBalance() - totalPrice);
        customerRepository.save(customer);
    }

    // 17- Deema: Customer get their orders
    public List<Orders> getCustomerOrders(Integer customerId){
        return orderRepository.findOrdersByCustomerId(customerId);
    }

    // 18- Sara: Customer get order by status
    public List<Orders> getOrdersByStatus(Integer customerId,String status){
        Customer customer=customerRepository.findCustomerById(customerId);
        List<Orders> ordersList=orderRepository.findOrdersByStatus(status);
        if (ordersList.isEmpty()){throw new ApiException("no orders found with this status");}
        List<Orders> newList=new ArrayList<>();
        for (Orders checkOrder:ordersList){
            if (checkOrder.getCustomer().equals(customer)){
                newList.add(checkOrder);
            }
        }
        if (newList.isEmpty()){throw new ApiException("no orders found for this customer");}
        return newList;
    }

    // 19- Sara: Customer get order by date
    public Orders getOrderByDate(Integer customerId, LocalDate date) {
        List<Orders> ordersList = orderRepository.findOrdersByCustomerId(customerId);
        if (ordersList.isEmpty()) {
            throw new ApiException("No orders found for customer: " + customerId);
        }
        for (Orders order : ordersList) {
            if (order.getDate().equals(date)) {
                return order;
            }
        }
        throw new ApiException("No order found for customer: " + customerId + " on date: " + date);
    }

}
