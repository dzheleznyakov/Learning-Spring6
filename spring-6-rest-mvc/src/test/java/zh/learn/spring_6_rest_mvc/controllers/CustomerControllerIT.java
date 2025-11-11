package zh.learn.spring_6_rest_mvc.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import zh.learn.spring_6_rest_mvc.entities.Customer;
import zh.learn.spring_6_rest_mvc.mappers.CustomerMapper;
import zh.learn.spring_6_rest_mvc.model.CustomerDTO;
import zh.learn.spring_6_rest_mvc.repositories.CustomerRepository;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testListCustomers() {
        List<CustomerDTO> dtos = customerController.listCustomers();

        assertThat(dtos.size()).isEqualTo(5);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyListOfCustomers() {
        customerRepository.deleteAll();
        List<CustomerDTO> dtos = customerController.listCustomers();

        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDto = customerController.getCustomerById(customer.getId());

        assertThat(customerDto).isNotNull();
    }

    @Test
    void testGetCustomerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> customerController.getCustomerById(UUID.randomUUID()));
    }

    @Transactional
    @Rollback
    @Test
    void testSaveNewCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("New Customer")
                .build();

        ResponseEntity<Void> responseEntity = customerController.createCustomer(customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] segments = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(segments[4]);

        Customer customer = customerRepository.findById(savedUUID).orElse(null);
        assertThat(customer).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void testUpdateExistingCustomer() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);
        customerDTO.setId(null);
        customerDTO.setVersion(null);
        final String customerName = "UPDATED";
        customerDTO.setName(customerName);

        ResponseEntity<Void> responseEntity = customerController.updateCustomerById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo(customerName);
    }

    @Test
    void testUpdateCustomerByIdNotFound() {
        assertThrows(
                NotFoundException.class,
                () -> customerController.updateCustomerById(UUID.randomUUID(), CustomerDTO.builder().build())
        );
    }

    @Transactional
    @Rollback
    @Test
    void testDeleteExistingCustomerById() {
        Customer customer = customerRepository.findAll().get(0);

        ResponseEntity<Void> responseEntity = customerController.deleteById(customer.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(foundCustomer).isNull();
    }

    @Test
    void testDeleteByIdNotFound() {
        assertThrows(
                NotFoundException.class,
                () -> customerController.deleteById(UUID.randomUUID())
        );
    }

    @Transactional
    @Rollback
    @Test
    void testPatchByIdFullUpdate() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("UPDATED")
                .build();

        ResponseEntity<Void> responseEntity = customerController.patchById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo(customerDTO.getName());
    }

    @Transactional
    @Rollback
    @Test
    void testPatchByIdEmptyUpdate() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO customerDTO = CustomerDTO.builder()
                .build();

        ResponseEntity<Void> responseEntity = customerController.patchById(customer.getId(), customerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Customer foundCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo(customer.getName());
    }

    @Test
    void testPatchByIdNotFound() {
        assertThrows(
                NotFoundException.class,
                () -> customerController.patchById(UUID.randomUUID(), CustomerDTO.builder().build())
        );
    }
}