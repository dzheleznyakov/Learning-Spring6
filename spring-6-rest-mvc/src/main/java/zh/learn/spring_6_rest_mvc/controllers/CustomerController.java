package zh.learn.spring_6_rest_mvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteById(@PathVariable("customerId") UUID id) {
        customerService.deleteById(id);

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomerById(@PathVariable("customerId") UUID id, @RequestBody Customer customer) {
        customerService.updateCustomerById(id, customer);

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId());
        return new ResponseEntity<>(
                headers,
                HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

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
