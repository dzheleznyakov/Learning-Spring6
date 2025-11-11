package zh.learn.spring_6_rest_mvc.services;

import zh.learn.spring_6_rest_mvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> listCustomers();

    Optional<CustomerDTO> getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customer);

    boolean deleteById(UUID id);

    boolean patchById(UUID id, CustomerDTO customer);
}
