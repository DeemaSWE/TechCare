package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.DTO.TechnicianDTO;
import com.example.techcare.Model.User;
import com.example.techcare.Service.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/technician")
@RequiredArgsConstructor
public class
TechnicianController {

    private final TechnicianService technicianService;

    // 52- Deema: Technician register
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid TechnicianDTO technician){
        technicianService.register(technician);
        return ResponseEntity.status(200).body(new ApiResponse("welcome to TechCare community"));
    }

    // 53- Deema: Admin get all technicians
    @GetMapping("/get-all")
    public ResponseEntity getAllTechnicians(){
        return ResponseEntity.status(200).body(technicianService.getAllTechnicians());
    }

    // 54- Deema: Technician get their profile
    @GetMapping("/get-profile")
    public ResponseEntity getMyInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(technicianService.getMyInfo(user.getId()));
    }

    // 55- Deema: Technician update their profile
    @PutMapping("/update")
    public ResponseEntity updateTechnician(@AuthenticationPrincipal User user, @RequestBody @Valid TechnicianDTO technician){
        technicianService.updateTechnician(user.getId(), technician);
        return ResponseEntity.status(200).body(new ApiResponse("Technician updated successfully"));
    }

    // 56- Deema: Admin delete a technician
    @DeleteMapping("/delete/{technicianId}")
    public ResponseEntity deleteTechnician(@PathVariable Integer technicianId){
        technicianService.deleteTechnician(technicianId);
        return ResponseEntity.status(200).body(new ApiResponse("Technician deleted successfully"));
    }

    // 57- Ahmad: Get technician by service
    @GetMapping("/service-technician/{service_id}")
    public ResponseEntity getTechnicianByService(@PathVariable Integer service_id){
        return ResponseEntity.status(200).body(technicianService.getTechnicianByService(service_id));
    }

    // 58- Ahmad: Get technician by id
    @GetMapping("get-by-id/{technicianId}")
    public ResponseEntity getTechnicianByName(@PathVariable Integer technicianId) {
        return ResponseEntity.status(200).body(technicianService.getTechnicianById(technicianId));
    }

    // 59- Ahmad: Get top rated technicians
    @GetMapping("/top-rated")
    public ResponseEntity getTopRatedTechnicians() {
        return ResponseEntity.status(200).body(technicianService.getTopRatedTechnicians());
    }

    // 60- Ahmad: Technician get trainer by level
    @GetMapping("/get-trainer-level/{level}")
    public  ResponseEntity getTrainersByLevel(@AuthenticationPrincipal User user,@PathVariable String level){
        return ResponseEntity.status(200).body(technicianService.getTrainersByLevel(user.getId(), level));

    }

    // 61- Ahmad: Filter technician by years of experience
    @GetMapping("/filter-by-experience/{years}")
    public ResponseEntity filterByExperience(@PathVariable Integer years){
        return ResponseEntity.status(200).body(technicianService.filterTechnicianByExperience(years));
    }
}
