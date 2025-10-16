package zh.learn.spring_6_rest_mvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import zh.learn.spring_6_rest_mvc.mappers.CustomerMapper;
import zh.learn.spring_6_rest_mvc.model.CustomerDTO;
import zh.learn.spring_6_rest_mvc.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> listCustomers() {
        return List.of();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.empty();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public void updateCustomerById(UUID id, CustomerDTO customer) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void patchById(UUID id, CustomerDTO customer) {

    }
}
