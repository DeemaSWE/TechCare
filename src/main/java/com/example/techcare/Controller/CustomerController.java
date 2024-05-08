package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.DTO.CustomerDTO;
import com.example.techcare.Model.User;
import com.example.techcare.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    // 6- Sara: Get all customers
    @GetMapping("/get-all")
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    // 7- Sara: Customer register
    @PostMapping("/register")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO customerDTO){//@AuthenticationPrincipal User user,//
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("welcome to TechCare community"));
    }

    // 8- Sara: Customer get their profile
    @GetMapping("/get-profile")
    public ResponseEntity getMyInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(customerService.getMyInfo(user.getId()));
    }

    // 9- Sara: Customer update their profile
    @PutMapping("/update")
    public ResponseEntity updateProfile(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO customerDTO){
        customerService.updateCustomer(user.getId(), customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("customer profile is updated"));
    }

    // 10- Sara: Admin delete customer
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user, @PathVariable Integer customerId){
        customerService.deleteCustomer(user.getId(), customerId);
        return ResponseEntity.status(200).body(new ApiResponse("customer deleted successfully"));
    }

    // 11- Sara: Customer rate technician
    @PutMapping("/rate-tech/{techID}/{reqId}/{rate}")
    public ResponseEntity rateTechByCustomer(@AuthenticationPrincipal User user,@PathVariable Integer techID,@PathVariable Integer reqId,@PathVariable Double rate){
        customerService.rateTech(user.getId(),techID,reqId,rate);
        return ResponseEntity.ok().body(new ApiResponse("Technician rated successfully"));
    }
}
