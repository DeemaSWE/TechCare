package com.example.techcare.Service;

import com.example.techcare.Api.ApiException;
import com.example.techcare.DTO.CustomerDTO;
import com.example.techcare.Model.*;
import com.example.techcare.Repository.AuthRepository;
import com.example.techcare.Repository.CustomerRepository;
import com.example.techcare.Repository.RequestsRepository;
import com.example.techcare.Repository.TechnicianRepository;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final TechnicianRepository technicianRepository;
    private final RequestsRepository requestsRepository;

    // 6- Sara: Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 7- Sara: Customer register
    public void registerCustomer(CustomerDTO customerDTO) {
        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        User user = new User(null, customerDTO.getUsername(), hashPassword, customerDTO.getName(),customerDTO.getEmail(), customerDTO.getPhoneNumber(), "CUSTOMER", customerDTO.getGender(), customerDTO.getAge(),null, null, null);

        authRepository.save(user);

        Customer customer = new Customer(null, customerDTO.getAddress(), customerDTO.getBalance(), user, null, null);
        customerRepository.save(customer);
    }

    // 8- Sara: Customer get their profile
    public Map<String, Object> getMyInfo(Integer id){
        Customer customer=customerRepository.findCustomerById(id);
        User user = customer.getUser();

        if (customer==null)
            throw new ApiException("customer not found");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("username", user.getUsername());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("phoneNumber", user.getPhoneNumber());
        map.put("gender", user.getGender());
        map.put("age", user.getAge());
        map.put("balance", customer.getBalance());
        map.put("address", customer.getAddress());
        map.put("requests", customer.getRequests());
        map.put("orders", customer.getOrders());

        return map;
    }

    // 9- Sara: Customer update their profile
    public void updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);
        User user = authRepository.findUserById(customer.getId());

        if (user.getCustomer().getId().equals(customerDTO.getId())) {
            //save in customerRepository
            customer.setAddress(customerDTO.getAddress());
            customer.setBalance(customerDTO.getBalance());
            customerRepository.save(customer);

            user.setUsername(customerDTO.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
            user.setName(customerDTO.getName());
            user.setEmail(customerDTO.getEmail());
            user.setPhoneNumber(customerDTO.getPhoneNumber());
            user.setGender(customerDTO.getGender());
            user.setAge(customerDTO.getAge());
            authRepository.save(user);
        } else throw new ApiException("Not match customer ids");
    }

    // 10- Sara: Admin delete customer
    public void deleteCustomer(Integer userId, Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        User user = authRepository.findUserById(customer.getId());

        if (customer == null)
            throw new ApiException("Customer not found");

        authRepository.delete(user);
    }


    // 11- Sara: Customer rate technician
    public void rateTech(Integer userId,Integer techID,Integer reqID,Double rate) {
        Technician tech = technicianRepository.findTechnicianById(techID);

        if (tech == null)
            throw new ApiException("the technician not found");

        Customer customer = customerRepository.findCustomerById(userId);

        Requests request = requestsRepository.findRequestsByCustomerAndTechnicianAndId(customer, tech, reqID);

        if (request==null)
            throw new ApiException("You don't have request for this technician");

        if(!request.getStatus().equalsIgnoreCase("accepted"))
            throw new ApiException("the request not accepted to rate the service");

        if(rate>5 || rate<0){
            throw new ApiException("rate must be between 0 and 5");
        }

        if(request.getIsCompleted().equals(false))
            throw new ApiException("the request not completed to rate technician");

        // Update technician's ratings
        double currentAvgRating = tech.getAvgRating();
        int ratingCount = tech.getRatingCount();
        double newAvgRating = ((currentAvgRating * ratingCount) + rate) / (ratingCount + 1);
        tech.setRatingCount(ratingCount + 1);
        tech.setAvgRating(newAvgRating);
        technicianRepository.save(tech);
    }


}
