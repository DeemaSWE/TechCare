package com.example.techcare.Service;

import com.example.techcare.Api.ApiException;
import com.example.techcare.DTO.ServicesDTO;
import com.example.techcare.Model.*;
import com.example.techcare.Repository.AuthRepository;
import com.example.techcare.Repository.CategoryRepository;
import com.example.techcare.Repository.ServicesRepository;
import com.example.techcare.Repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ServicesService {

    private final ServicesRepository servicesRepository;
    private final CategoryRepository categoryRepository;
    private final TechnicianRepository technicianRepository;
    private final AuthRepository authRepository;

    // 42- Deema: Get all services
    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    // 43- Deema: Technician add maintenance and consulting services for customers
    public void addCustomerService(Integer userId, ServicesDTO servicesDTO) {
        Category category = categoryRepository.findCategoryById(servicesDTO.getCategoryId());
        Technician technician = technicianRepository.findTechnicianById(userId);

        if(category == null)
            throw new ApiException("Category not found");

        if(!servicesDTO.getType().equalsIgnoreCase("maintenance") && !servicesDTO.getType().equalsIgnoreCase("consulting"))
            throw new ApiException("Invalid service type");

        Services service = new Services(null, servicesDTO.getDescription(), servicesDTO.getPrice(), servicesDTO.getType(),0.0, 0, null, technician, category, null);

        servicesRepository.save(service);
    }

    // 44- Deema: Technician add training services for trainers
    public void addTrainerService(Integer userId, ServicesDTO servicesDTO) {
        Category category = categoryRepository.findCategoryById(servicesDTO.getCategoryId());
        Technician technician = technicianRepository.findTechnicianById(userId);

        if(category == null)
            throw new ApiException("Category not found");

        if(!servicesDTO.getType().equalsIgnoreCase("training"))
            throw new ApiException("Invalid service type");

        if(servicesDTO.getHours() == null)
            throw new ApiException("Training hours must be provided");

        if(servicesDTO.getHours() < 1)
            throw new ApiException("Training hours must be greater than 1");

        Services service = new Services(null, servicesDTO.getDescription(), servicesDTO.getPrice(), servicesDTO.getType(),0.0, 0, servicesDTO.getHours() , technician, category, null);

        servicesRepository.save(service);
    }

    // 45- Deema: Technician update a service
    public void updateService(Integer userId, Integer serviceId, ServicesDTO updatedService) {
        Services service = servicesRepository.findServicesById(serviceId);
        Category category = categoryRepository.findCategoryById(updatedService.getCategoryId());

        if(service == null)
            throw new ApiException("Service not found");

        if(category == null)
            throw new ApiException("Category not found");

        if(service.getTechnician().getId() != userId)
            throw new ApiException("You are not authorized to update this service");

        service.setDescription(updatedService.getDescription());
        service.setPrice(updatedService.getPrice());
        service.setCategory(category);

        if(updatedService.getType().equalsIgnoreCase("training") && updatedService.getHours() != null)
            service.setHours(updatedService.getHours());

        servicesRepository.save(service);
    }

    // 46- Deema: Technician delete a service
    public void deleteService(Integer userId, Integer serviceId) {
        Services service = servicesRepository.findServicesById(serviceId);

        if(service == null)
            throw new ApiException("Service not found");

        if(service.getTechnician().getId() != userId)
            throw new ApiException("You are not authorized to delete this service");

        servicesRepository.delete(service);
    }

    // 47- Deema: Technician get their services
    public Set<Services> getTechnicianServices(Integer userId) {
        Technician technician = technicianRepository.findTechnicianById(userId);

        return technician.getServices();
    }

    // 48- Deema: Filter services by price range
    public List<Services> getServicesByPrice(Double minPrice, Double maxPrice) {
        if(minPrice < 0 || maxPrice < 0)
            throw new ApiException("Price must be greater than 0");

        if(minPrice > maxPrice)
            throw new ApiException("Minimum price must be less than maximum price");

        return servicesRepository.findServicesByPriceBetween(minPrice, maxPrice);
    }

    // 49- Deema: Get services by type (maintenance, consulting, training)
    public List<Services> getServicesByType(String type) {
        if (!type.equals("maintenance") && !type.equals("consulting") && !type.equals("training"))
            throw new ApiException("Invalid type");

        return servicesRepository.findServicesByType(type);
    }

    // 50- Ahmad: Get services by category (laptops, smartphone...)
    public Set<Services> getServicesByCategory(Integer categoryId) {
        Category category = categoryRepository.findCategoryById(categoryId);

        if(category == null)
            throw new ApiException("Category not found");

        return category.getServices();
    }

    // 51- Ahmad: Get training services by hours
    public List<Services> getServicesByTrainingHours(Integer hours) {
        return servicesRepository.findServicesByHours(hours);
    }
}
