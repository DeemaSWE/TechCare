package com.example.techcare.Service;



import com.example.techcare.Api.ApiException;
import com.example.techcare.DTO.RequestDTO;
import com.example.techcare.Model.*;
import com.example.techcare.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestsRepository requestsRepository;
    private final CustomerRepository customerRepository;
    private final TechnicianRepository technicianRepository;
    private final TrainerRepository trainerRepository;
    private final ServicesRepository servicesRepository;
    private final AuthRepository authRepository;

    // 29- Sara: Customer get their requests
    public List<Requests> customerGetMyRequests(Integer customerId){
        Customer customer=customerRepository.findCustomerById(customerId);
        List<Requests> myRequstsList=requestsRepository.findRequestsByCustomer(customer);
        if (myRequstsList.isEmpty()){
            throw new ApiException("the list is empty");
        }
        return myRequstsList;
    }

    // 30- Sara: Customer send a request
    public void addRequestCustomer(Integer customerId, Integer serviceID) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Services service = servicesRepository.findServicesById(serviceID);
        if (service == null) {
            throw new ApiException("the service id not found");
        }
        Technician tech=service.getTechnician();

        if(service.getType().equalsIgnoreCase("training")){
            throw new ApiException("this service is only for trainers");
        }

        if (tech==null){throw new ApiException("this service not provide by this technician");}
        Requests request = new Requests(null, service.getType(),"pending",0.0, LocalDate.now(), false,customer, tech, null, service);
        requestsRepository.save(request);
    }

    // 31- Sara: Trainer send a request
    public void addRequestTrainer(Integer trainerId, Integer serviceId){
        Trainer trainer =trainerRepository.findTrainerById(trainerId);
        Services services=servicesRepository.findServicesById(serviceId);

        if (services==null){
            throw new ApiException("the service id not found");
        }

        Technician technician=services.getTechnician();

        if(services.getType().equalsIgnoreCase("maintenance") || services.getType().equalsIgnoreCase("consulting")){
            throw new ApiException("this service is only for customers");
        }

        if (technician==null){throw new ApiException("this service not provide by this technician");}
        //condition if trainer is advanced level and pass 180 hours
        if (trainer.getLevel().equalsIgnoreCase("advance") && trainer.getHours()>180){
            throw new ApiException("you have done train course completely ");
        }

        Requests request = new Requests(null, services.getType(),"pending",0.0, LocalDate.now() , false,null, technician, trainer, services);
        request.setRating(0.0);
        requestsRepository.save(request);
    }

    // 32- Sara: Admin delete a request
    public void deleteRequest(Integer customerId,Integer requestId) {//how i get request number ..from path variable??
        Requests request = requestsRepository.findRequestsById(requestId);
        if (request == null) {
            throw new ApiException("your request not found");
        }
        if (request.getCustomer().getId().equals(customerId)){
            if (request.getStatus().equalsIgnoreCase("rejected")) {
                requestsRepository.delete(request);}

        } else throw new ApiException("you dont have permission to delete this request ...");
    }

    // 33- Deema: Technician accept/reject a request
    public void setRequestStatus(Integer technicianId, String status, Integer requestId){
        Requests request=requestsRepository.findRequestsById(requestId);

        if (request==null)
            throw new ApiException("the request not found");

        if (request.getTechnician().getId() != technicianId)
            throw new ApiException("you are not authorized to update this request");

        if(request.getStatus().equals("accepted"))
            throw new ApiException("Request already accepted");

        if(request.getStatus().equals("rejected"))
            throw new ApiException("Request already rejected");

        if (status.equals("accept")){
            request.setStatus("accepted");
            requestsRepository.save(request);

        } else if (status.equals("reject")){
            request.setStatus("rejected");
            requestsRepository.save(request);

        } else
            throw new ApiException("Invalid status");
    }

    // 34- Deema: Technician get requests by status (pending, accepted, rejected)
    public List<Requests> getRequestsByStatus(Integer technicianId, String status){
        Technician technician=technicianRepository.findTechnicianById(technicianId);

        if(!status.equals("accepted") && !status.equals("rejected") && !status.equals("pending"))
            throw new ApiException("Invalid status");

        List<Requests> requestsList = requestsRepository.findRequestsByTechnicianAndStatus(technician, status);

        return requestsList;
    }

    // 35- Sara: Technician change customer requests status to be completed
    public void changeCustomerStatus(Integer techId, Integer reqId) {//Q-is better for tech write status or just enter the request-ID
        Requests request = requestsRepository.findRequestsById(reqId);
        if (request.getTechnician().getId().equals(techId)) {
            if (! request.getStatus().equalsIgnoreCase("accepted")) {
                throw new ApiException("the request not accepted to be changed");
            }else
            if (request.getIsCompleted().equals(true)) {
                throw new ApiException("the status already completed");
            } else
                request.setIsCompleted(true);
            requestsRepository.save(request);

        }
        else throw new ApiException("you dont have permission to change request status");
    }

    // 36- Sara: Customer Rate Request
    public void customerRateRequest(Integer userId, Integer reqID, Double rate) {
        Customer customer=customerRepository.findCustomerById(userId);
        Requests request = requestsRepository.findRequestsByCustomerAndId(customer, reqID);

        if (request==null)
            throw new ApiException("the request not found");

        if(rate<1 || rate>5)
            throw new ApiException("Rate should be between 1 and 5");

        if(!request.getStatus().equalsIgnoreCase("accepted"))
            throw new ApiException("the request not accepted to rate the service");

        if(request.getIsCompleted().equals(false))
            throw new ApiException("the request not completed to rate technician");

        request.setRating(rate);
        requestsRepository.save(request);

    }
    // 37- Sara: Customer get Requests by status
    public List<Requests> getCustomerRequstsByStatus(Integer userId,String status){
        Customer customer=customerRepository.findCustomerById(userId);

        if(!status.equalsIgnoreCase("accepted") && !status.equalsIgnoreCase("rejected") && !status.equalsIgnoreCase("pending"))
            throw new ApiException("Invalid status");

        List<Requests> list=requestsRepository.findRequestsByCustomerAndStatus(customer,status);
        if (list.isEmpty()){throw new ApiException("no result found");}

        return list;
    }

    // 38- Sara: Technician change trainer requests status to be completed and set hours
    public void changeTrainerStatus(Integer techId, Integer reqId) {//Q-is better for tech write status or just enter the request-ID
        Requests request = requestsRepository.findRequestsById(reqId);
        if (request.getTechnician().getId().equals(techId)) {
            if (! request.getStatus().equalsIgnoreCase("accepted")) {
                throw new ApiException("the request not accepted to be changed");
            }else
            if (request.getIsCompleted().equals(true)) {
                throw new ApiException("the status already completed");
            } else
                request.setIsCompleted(true);
            request.getTrainer().setHours(request.getTrainer().getHours() + request.getService().getHours());
            request.getTrainer().setLevel(setLevel(request.getTrainer().getHours()));
            requestsRepository.save(request);

        }
        else throw new ApiException("you dont have permission to change request status");
    }

    public String setLevel(Integer hours){
        if (hours>=0 && hours<60){
            return "beginner";
        }else if (hours>=60 && hours<120){
            return  "middle";
        } else if (hours >=120 ){
            return "advance";
        }
        return "out of boundaries";
    }

    // 39- Ahmed: Trainer Rate Request
    public void trainerRateRequest(Integer userId, Integer reqID, Double rate) {
        Trainer trainer = trainerRepository.findTrainerById(userId);
        Requests request = requestsRepository.findRequestsByTrainerAndId(trainer, reqID);

        if (request==null)
            throw new ApiException("the request not found");

        if(rate<1 || rate>5)
            throw new ApiException("Rate should be between 1 and 5");

        if(!request.getStatus().equalsIgnoreCase("accepted"))
            throw new ApiException("the request not accepted to rate the service");

        if(request.getIsCompleted().equals(false))
            throw new ApiException("the request not completed to rate technician");

        request.setRating(rate);
        requestsRepository.save(request);


    }

    // 40- Sara: Technician get requests
    public List<Requests> techGetRequests(Integer techId) {
        Technician tech = technicianRepository.findTechnicianById(techId);
        List<Requests> myRequstsList = requestsRepository.findRequestsByTechnician(tech);
        if (myRequstsList.isEmpty()) {
            throw new ApiException("the list is empty");
        }
        return myRequstsList;
    }

    // 41- Sara: Trainer get their requests
    public List<Requests> trainerGetMyRequests(Integer techId) {
        Trainer trainer = trainerRepository.findTrainerById(techId);
        List<Requests> myRequstsList = requestsRepository.findRequestsByTrainer(trainer);
        if (myRequstsList.isEmpty()) {
            throw new ApiException("the list is empty");
        }
        return myRequstsList;
    }

}
