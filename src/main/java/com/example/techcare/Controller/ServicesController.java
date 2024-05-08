package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.DTO.ServicesDTO;
import com.example.techcare.Model.User;
import com.example.techcare.Service.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    // 42- Deema: Get all services
    @GetMapping("get-all")
    public ResponseEntity getAllServices(){
        return ResponseEntity.status(200).body(servicesService.getAllServices());
    }

    // 43- Deema: Technician add maintenance and consulting services for customers
    @PostMapping("/add-customer-service")
    public ResponseEntity addCustomerService(@AuthenticationPrincipal User user, @RequestBody @Valid ServicesDTO service){
        servicesService.addCustomerService(user.getId(), service);
        return ResponseEntity.status(200).body(new ApiResponse("Service added successfully"));
    }

    // 44- Deema: Technician add training services for trainers
    @PostMapping("/add-trainer-service")
    public ResponseEntity addTrainerService(@AuthenticationPrincipal User user, @RequestBody @Valid ServicesDTO service){
        servicesService.addTrainerService(user.getId(), service);
        return ResponseEntity.status(200).body(new ApiResponse("Service added successfully"));
    }

    // 45- Deema: Technician update a service
    @PutMapping("/update/{serviceId}")
    public ResponseEntity updateService(@AuthenticationPrincipal User user, @PathVariable Integer serviceId, @RequestBody @Valid ServicesDTO updatedService){
        servicesService.updateService(user.getId(), serviceId, updatedService);
        return ResponseEntity.status(200).body(new ApiResponse("Service updated successfully"));
    }

    // 46- Deema: Technician delete a service
    @DeleteMapping("delete/{serviceId}")
    public ResponseEntity deleteService(@AuthenticationPrincipal User user, @PathVariable Integer serviceId){
        servicesService.deleteService(user.getId(), serviceId);
        return ResponseEntity.status(200).body(new ApiResponse("Service deleted successfully"));
    }

    // 47- Deema: Technician get their services
    @GetMapping("/get-tech-services")
    public ResponseEntity getTechnicianServices(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(servicesService.getTechnicianServices(user.getId()));
    }

    // 48- Deema: Filter services by price range
    @GetMapping("/filter-by-price/{min}/{max}")
    public ResponseEntity getServicesByPrice(@PathVariable Double min, @PathVariable Double max){
        return ResponseEntity.status(200).body(servicesService.getServicesByPrice(min, max));
    }

    // 49- Deema: Get services by type (maintenance, consulting, training)
    @GetMapping("/get-by-type/{type}")
    public ResponseEntity getServicesByType(@PathVariable String type){
        return ResponseEntity.status(200).body(servicesService.getServicesByType(type));
    }

    // 50- Ahmad: Get services by category (laptops, smartphone...)
    @GetMapping("/get-by-category/{categoryId}")
    public ResponseEntity getServicesByCategory(@PathVariable Integer categoryId){
        return ResponseEntity.status(200).body(servicesService.getServicesByCategory(categoryId));
    }

    // 51- Ahmad: Get training services by hours
    @GetMapping("/get-by-hours/{hours}")
    public ResponseEntity getServicesByHours(@PathVariable Integer hours){
        return ResponseEntity.status(200).body(servicesService.getServicesByTrainingHours(hours));
    }
}
