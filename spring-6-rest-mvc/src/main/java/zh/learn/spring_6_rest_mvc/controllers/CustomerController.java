package zh.learn.spring_6_rest_mvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zh.learn.spring_6_rest_mvc.model.Customer;
import zh.learn.spring_6_rest_mvc.services.CustomerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers() {
        log.debug("Get all Customers - in controller");
        return customerService.listCustomers();
    }

    @RequestMapping(value = "/{customerId}")
    public Customer getCustomerById(
            @PathVariable("customerId") UUID id
    ) {
        log.debug("Get Customer by Id - in controller");
        return customerService.getCustomerById(id);
    }

}
