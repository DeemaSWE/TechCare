package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.DTO.RequestDTO;
import com.example.techcare.Model.Requests;
import com.example.techcare.Model.User;
import com.example.techcare.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
@RestController
public class RequestController {
    private final RequestService requestService;

    // 29- Sara: Customer get their requests
    @GetMapping("/customer-get-my-request")
    public ResponseEntity customerGetMyRequest(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(requestService.customerGetMyRequests(user.getId()));
    }

    // 30- Sara: Customer send a request
    @PostMapping("/customer-send-request/{serviceId}")
    public ResponseEntity addRequest(@AuthenticationPrincipal User user, @PathVariable Integer serviceId){
        requestService.addRequestCustomer(user.getId(), serviceId);
        return ResponseEntity.status(200).body(new ApiResponse("your request sent successfully"));

    }

    // 31- Sara: Trainer send a request
    @PostMapping("/trainer-send-request/{serviceId}")
    public ResponseEntity addRequestTrainer(@AuthenticationPrincipal User user, @PathVariable Integer serviceId){
        requestService.addRequestTrainer(user.getId(),serviceId);
        return ResponseEntity.status(200).body(new ApiResponse("your request sent successfully"));

    }

    // 32- Sara: Admin delete a request
    @DeleteMapping("/delete/{request_num}")
    public ResponseEntity deleteRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_num){
        requestService.deleteRequest(user.getId(), request_num);
        return ResponseEntity.status(200).body(new ApiResponse("your request deleted successfully"));

    }

    // 33- Deema: Technician accept/reject a request
    @PutMapping("/set-status/{status}/{requestId}")
    public ResponseEntity setRequestStatus(@AuthenticationPrincipal User user, @PathVariable String status, @PathVariable Integer requestId){
        requestService.setRequestStatus(user.getId(), status, requestId);
        return ResponseEntity.status(200).body(new ApiResponse(status + " request successfully"));
    }

    // 34- Deema: Technician get requests by status (pending, accepted, rejected)
    @GetMapping("/get-by-status/{status}")
    public ResponseEntity getRequestsByStatus(@AuthenticationPrincipal User user, @PathVariable String status){
        return ResponseEntity.status(200).body(requestService.getRequestsByStatus(user.getId(), status));
    }

    // 35- Sara: Technician change customer requests status to be completed
    @PutMapping("/change-customer-request-status/{reqId}")
    public ResponseEntity changeRequestStatus(@AuthenticationPrincipal User user, @PathVariable Integer reqId){
        requestService.changeCustomerStatus(user.getId(), reqId);
        return ResponseEntity.ok().body(new ApiResponse("the status change successsfully"));
    }

    // 36- Sara: Customer Rate Request
    @PutMapping("/customer-rate-request/{reqId}/{rate}")
    public ResponseEntity customerRateRequest(@AuthenticationPrincipal User user ,@PathVariable Integer reqId,@PathVariable Double rate){
        requestService.customerRateRequest(user.getId(), reqId,rate);
        return ResponseEntity.ok().body(new ApiResponse("thank you , you rate request successfully"));
    }

    // 37- Sara: Customer get Requests by status
    @GetMapping("/customer-request-status/{status}")
    public ResponseEntity getRequestByStatus(@AuthenticationPrincipal User user,@PathVariable String status){
        return ResponseEntity.status(200).body(requestService.getCustomerRequstsByStatus(user.getId(),status));
    }

    // 38- Sara: Technician change requests status to be completed and set hours
    @PutMapping("/change-trainer-request-status/{reqId}")
    public ResponseEntity changeTrainerRequestandSethours(@AuthenticationPrincipal User user ,@PathVariable Integer reqId){
        requestService.changeTrainerStatus(user.getId(), reqId);
        return ResponseEntity.ok().body(new ApiResponse("the status changed successfully"));
    }

    // 39- Ahmed: Trainer Rate Request
    @PutMapping("/trainer-rate-request/{reqId}/{rate}")
    public ResponseEntity trainerRateRequest(@AuthenticationPrincipal User user ,@PathVariable Integer reqId,@PathVariable Double rate){
        requestService.trainerRateRequest(user.getId(), reqId,rate);
        return ResponseEntity.ok().body(new ApiResponse("thank you , you rate request successfully"));
    }

    // 40- Sara: Technician get requests
    @GetMapping("/tech-get-request")//for tech
    public ResponseEntity techGetMyRequest(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(requestService.techGetRequests(user.getId()));
    }

    // 41- Sara: Trainer get their requests
    @GetMapping("/trainer-get-request")// for trainer
    public ResponseEntity trainerGetMyRequest(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(requestService.trainerGetMyRequests(user.getId()));
    }
}
