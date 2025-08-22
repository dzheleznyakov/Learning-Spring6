package zh.learn.spring_6_rest_mvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zh.learn.spring_6_rest_mvc.model.Customer;
import zh.learn.spring_6_rest_mvc.services.CustomerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Void> patchById(@PathVariable("customerId") UUID id, @RequestBody Customer customer) {
        customerService.patchById(id, customer);
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("customerId") UUID id) {
        customerService.deleteById(id);

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Void> updateCustomerById(@PathVariable("customerId") UUID id, @RequestBody Customer customer) {
        customerService.updateCustomerById(id, customer);

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId());
        return new ResponseEntity<>(
                headers,
                HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> listCustomers() {
        log.debug("Get all Customers - in controller");
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerById(
            @PathVariable("customerId") UUID id
    ) {
        log.debug("Get Customer by Id - in controller");
        return customerService.getCustomerById(id);
    }

}
