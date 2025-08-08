package zh.learn.spring_6_rest_mvc.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import zh.learn.spring_6_rest_mvc.model.Customer;
import zh.learn.spring_6_rest_mvc.services.CustomerService;
import zh.learn.spring_6_rest_mvc.services.CustomerServiceImpl;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CustomerService customerService;

    private final Customer testCustomer = new CustomerServiceImpl().listCustomers().get(0);

    @Test
    void testGetCustomerById() throws Exception {
        UUID customerId = testCustomer.getId();
        given(customerService.getCustomerById(customerId)).willReturn(testCustomer);

        mockMvc.perform(get("/api/v1/customer/" + customerId)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customerId.toString())))
                .andExpect(jsonPath("$.name", is(testCustomer.getName())));
    }
}