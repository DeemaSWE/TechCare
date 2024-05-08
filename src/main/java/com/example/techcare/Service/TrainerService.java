package com.example.techcare.Service;

import com.example.techcare.Api.ApiException;
import com.example.techcare.DTO.TrainerDTO;
import com.example.techcare.Model.Requests;
import com.example.techcare.Model.Technician;
import com.example.techcare.Model.Trainer;
import com.example.techcare.Model.User;
import com.example.techcare.Repository.AuthRepository;
import com.example.techcare.Repository.RequestsRepository;
import com.example.techcare.Repository.TechnicianRepository;
import com.example.techcare.Repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final AuthRepository authRepository;
    private final RequestsRepository requestsRepository;
    private final TechnicianRepository technicianRepository;


    // 62- Ahmad: Trainer register
    public void registerTrainer(TrainerDTO trainerDTO){
        String hashPassword = new BCryptPasswordEncoder().encode(trainerDTO.getPassword());
        User user = new User(null, trainerDTO.getUsername(), hashPassword, trainerDTO.getName(),trainerDTO.getEmail(), trainerDTO.getPhoneNumber(), "TRAINER", trainerDTO.getGender(), trainerDTO.getAge(),null, null, null);
        authRepository.save(user);

        Trainer trainer=new Trainer(null,"beginner",0,user,null);
        trainerRepository.save(trainer);
    }

    // 63- Ahmad: Admin get all trainers
    public List<Trainer> getAllTrainer() {
        return trainerRepository.findAll();
    }

    // 64- Ahmad: Trainer get their profile
    public Map<String, Object> getMyInfo(Integer id){
        Trainer trainer=trainerRepository.findTrainerById(id);
        User user = trainer.getUser();

        if (trainer==null)
            throw new ApiException("Trainer not found");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("username", user.getUsername());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("phoneNumber", user.getPhoneNumber());
        map.put("gender", user.getGender());
        map.put("age", user.getAge());
        map.put("level", trainer.getLevel());
        map.put("hours", trainer.getHours());
        map.put("requests", trainer.getRequests());

        return map;
    }

    // 65- Ahmad: Trainer update their profile
    public void updateTrainer(Integer trainer_id, TrainerDTO trainer) {
        Trainer trainer1 = trainerRepository.findTrainerById(trainer_id);
        User user = authRepository.findUserById(trainer1.getId());

        user.setName(trainer.getName());
        user.setAge(trainer.getAge());
        user.setEmail(trainer.getEmail());
        user.setPhoneNumber(trainer.getPhoneNumber());
        user.setGender(trainer.getGender());
        authRepository.save(user);
    }

    // 66- Ahmad: Admin delete a trainer
    public void deleteTrainer(Integer trainer_id){
        Trainer trainer = trainerRepository.findTrainerById(trainer_id);
        User user = authRepository.findUserById(trainer_id);

        if(trainer == null){
            throw new ApiException("Trainer does not exists");
        }

        authRepository.delete(user);
    }

    // 67- Ahmad: Trainer rate technician
    public void trainerRateTech(Integer userId,Integer techID,Integer reqID,Double rate) {
        Technician tech = technicianRepository.findTechnicianById(techID);
        if (tech == null) {
            throw new ApiException("the technician not found");
        }
        Trainer trainer=trainerRepository.findTrainerById(userId);

        List<Requests> requestsList = requestsRepository.findRequestsByTrainer(trainer);
        for (Requests request : requestsList) {
            if (request.getId() .equals(reqID)) {
                if (!(request.getTechnician().getId().equals(techID)) ){
                    throw new ApiException("this technician not in your requests ");

                }
                if (!(request.getStatus().equalsIgnoreCase("accepted"))) {
                    throw new ApiException("the request not accepted to rate the service");
                }
                //check if request complete or not
                if (!request.getIsCompleted()) {
                    throw new ApiException("the request not completed to rate technician");
                }
                if (rate >= 1 && rate <= 5) {//no need for validation here bc we add in the model
                    // Update technician's ratings
                    double currentAvgRating = tech.getAvgRating();
                    int ratingCount = tech.getRatingCount();
                    double newAvgRating = ((currentAvgRating * ratingCount) + rate) / (ratingCount + 1);
                    tech.setRatingCount(ratingCount + 1);
                    tech.setAvgRating(newAvgRating);
                    technicianRepository.save(tech);
                }else throw new ApiException("enter number between1-5");


            } }
        if (requestsList.isEmpty()){
            throw new ApiException("No requests found for this trainer");
        }

    }
}