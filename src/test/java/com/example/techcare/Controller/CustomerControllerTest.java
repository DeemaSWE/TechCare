package com.example.techcare.Controller;
import com.example.techcare.DTO.CustomerDTO;
import com.example.techcare.Service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class CustomerControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
//5-sara
    @Test
    public void testGetAllCustomers() throws Exception {

        CustomerDTO customer1 = new CustomerDTO(1,"sara", "123456", "sara","sara@Email.com", 25, "05011111111","female","address",1000.00);
        CustomerDTO customer2 = new CustomerDTO(1,"maha", "123456", "sara","maha@Email.com", 25, "05011111111","female","address",1000.00);
        CustomerDTO customer3 = new CustomerDTO(1,"nada", "123456", "sara","nada@Email.com", 25, "05011111111","female","address",1000.00);

        mockMvc.perform(get("/api/v1/customer/get-all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(3));

        verify(customerService, times(1)).getAllCustomers();
    }



}
