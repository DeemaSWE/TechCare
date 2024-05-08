package com.example.techcare.Service;

import com.example.techcare.Api.ApiException;
import com.example.techcare.DTO.TechnicianDTO;
import com.example.techcare.Model.*;
import com.example.techcare.Repository.AuthRepository;
import com.example.techcare.Repository.CategoryRepository;
import com.example.techcare.Repository.ServicesRepository;
import com.example.techcare.Repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final AuthRepository authRepository;
    private  final CategoryRepository categoryRepository;
    private final ServicesRepository servicesRepository;


    // 52- Deema: Technician register
    public void register(TechnicianDTO technicianDTO){
        String hashPassword = new BCryptPasswordEncoder().encode(technicianDTO.getPassword());
        User user = new User(null, technicianDTO.getUsername(), hashPassword, technicianDTO.getName(),technicianDTO.getEmail(), technicianDTO.getPhoneNumber(), "TECHNICIAN", technicianDTO.getGender(), technicianDTO.getAge(),null, null, null);
        authRepository.save(user);

        Technician technician = new Technician(null, 0.0, 0, technicianDTO.getYearsOfExperience(), technicianDTO.getCertificateSCE() ,user, null, null);
        technicianRepository.save(technician);
    }

    // 53- Deema: Admin get all technicians
    public List<Technician> getAllTechnicians(){
        return technicianRepository.findAll();
    }

    // 54- Deema: Technician get their profile
    public Map<String, Object> getMyInfo(Integer id){
        Technician technician=technicianRepository.findTechnicianById(id);
        User user = technician.getUser();

        if (technician==null)
            throw new ApiException("Technician not found");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("username", user.getUsername());
        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("phoneNumber", user.getPhoneNumber());
        map.put("gender", user.getGender());
        map.put("age", user.getAge());
        map.put("avgRating", technician.getAvgRating());
        map.put("ratingCount", technician.getRatingCount());
        map.put("yearsOfExperience", technician.getYearsOfExperience());
        map.put("certificateSCE", technician.getCertificateSCE());
        map.put("services", technician.getServices());

        return map;
    }

    // 55- Deema: Technician update their profile
    public void updateTechnician(Integer userId, TechnicianDTO updatedTechnician) {
        Technician technician = technicianRepository.findTechnicianById(userId);
        User user = technician.getUser();

        user.setUsername(updatedTechnician.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(updatedTechnician.getPassword()));
        user.setName(updatedTechnician.getName());
        user.setEmail(updatedTechnician.getEmail());
        user.setPhoneNumber(updatedTechnician.getPhoneNumber());
        user.setGender(updatedTechnician.getGender());
        user.setAge(updatedTechnician.getAge());
        authRepository.save(user);

        if(updatedTechnician.getAvgRating() != 0 && updatedTechnician.getRatingCount() != 0) {
            technician.setAvgRating(updatedTechnician.getAvgRating());
            technician.setRatingCount(updatedTechnician.getRatingCount());
        }

        technician.setCertificateSCE(updatedTechnician.getCertificateSCE());
        technician.setYearsOfExperience(updatedTechnician.getYearsOfExperience());
        technicianRepository.save(technician);
    }

    // 56- Deema: Admin delete a technician
    public void deleteTechnician(Integer technicianId) {
        Technician technician = technicianRepository.findTechnicianById(technicianId);
        User user = authRepository.findUserById(technicianId);

        if(technician == null)
            throw new ApiException("Technician not found");

        authRepository.delete(user);
    }


    // 57- Ahmad: Get technician by service
    public Technician getTechnicianByService(Integer service_id){
        Services services=servicesRepository.findServicesById(service_id);
        if(services == null){
            throw new ApiException("service does not exists");
        }
        return services.getTechnician();
    }

    // 58- Ahmad: Get technician by id
    public Technician getTechnicianById(Integer id) {
       Technician tech = technicianRepository.findTechnicianById(id);
        if (tech == null) {
            throw new ApiException("Technician not found");
        }
        return tech;
    }

    // 59- Ahmad: Get top rated technicians
    public List<Technician> getTopRatedTechnicians() {
        return technicianRepository.findTop10ByOrderByAvgRatingDesc();
    }

    // 60- Ahmad: Technician get trainer by level
    public List<Trainer> getTrainersByLevel(Integer techId, String level) {
        Technician tech = technicianRepository.findTechnicianById(techId);
        Set<Requests> requestsList = tech.getRequests();
        List<Trainer> listTrainer = new ArrayList<>();
        for (Requests request : requestsList) {
            if (request.getTrainer().getLevel().equalsIgnoreCase(level)){
                listTrainer.add(request.getTrainer());
            }
        }
        if (listTrainer.isEmpty()){throw new ApiException("no result founded");}
        return listTrainer;
    }

    // 61- Ahmad: Filter technician by years of experience
    public List<Technician> filterTechnicianByExperience(Integer yearsOfExperience) {
        return technicianRepository.findTechnicianByYearsOfExperience(yearsOfExperience);
    }

}
