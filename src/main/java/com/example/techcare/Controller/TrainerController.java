package com.example.techcare.Controller;

import com.example.techcare.Api.ApiResponse;
import com.example.techcare.DTO.TrainerDTO;
import com.example.techcare.Model.Trainer;
import com.example.techcare.Model.User;
import com.example.techcare.Service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trainer")
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;
    Logger logger = LoggerFactory.getLogger(TrainerController.class);

    // 62- Ahmad: Trainer register
    @PostMapping("/register")
    public ResponseEntity registerTrainer(@RequestBody @Valid TrainerDTO trainerDTO){
        trainerService.registerTrainer(trainerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("welcome to TechCare community"));
    }

    // 63- Ahmad: Admin get all trainers
    @GetMapping("/get-all")
    public ResponseEntity getAllTrainer(){
        logger.info("get all Trainers");
        return ResponseEntity.ok(trainerService.getAllTrainer());
    }

    // 64- Ahmad: Trainer get their profile
    @GetMapping("/get-profile")
    public ResponseEntity getProfile(@AuthenticationPrincipal User user){
        //   logger.info("get Trainer profile");
        return ResponseEntity.ok(trainerService.getMyInfo(user.getId()));
    }

    // 65- Ahmad: Trainer update their profile
    @PutMapping("/update")
    public ResponseEntity updateTrainer(@AuthenticationPrincipal User user, @RequestBody @Valid TrainerDTO trainer){
        trainerService.updateTrainer(user.getId(), trainer);
        //  logger.info("Trainer updated");
        return ResponseEntity.ok(new ApiResponse("Trainer updated"));
    }

    // 66- Ahmad: Admin delete a trainer
    @DeleteMapping("/delete/{trainer_id}")
    public ResponseEntity deleteTrainer(@PathVariable Integer trainer_id){
        trainerService.deleteTrainer(trainer_id);
        logger.info("Trainer removed");
        return ResponseEntity.ok(new ApiResponse("Trainer deleted"));
    }

    // 67- Ahmad: Trainer rate technician
    @PutMapping("/rate-tech/{techID}/{reqId}/{rate}")
    public ResponseEntity rateTechByTrainer(@AuthenticationPrincipal User user,@PathVariable Integer techID,@PathVariable Integer reqId,@PathVariable Double rate){
        trainerService.trainerRateTech(user.getId(),techID,reqId,rate);
        return ResponseEntity.ok().body(new ApiResponse("Technician rated successfully"));
    }
}
