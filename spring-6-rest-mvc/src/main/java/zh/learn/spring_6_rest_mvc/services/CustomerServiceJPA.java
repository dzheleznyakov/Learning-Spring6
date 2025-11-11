package zh.learn.spring_6_rest_mvc.services;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import zh.learn.spring_6_rest_mvc.entities.Customer;
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
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .toList();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto);
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return Optional.of(customer)
                .map(customerMapper::customerDtoToCustomer)
                .map(customerRepository::save)
                .map(customerMapper::customerToCustomerDto)
                .get();
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customer) {
        Optional<CustomerDTO>[] customerContainer = new Optional[1];

        customerRepository.findById(id)
                .ifPresentOrElse(
                        foundCustomer -> {
                            foundCustomer.setName(customer.getName());
                            Customer savedCustomer = customerRepository.save(foundCustomer);
                            customerContainer[0] = Optional.of(
                                    customerMapper.customerToCustomerDto(savedCustomer)
                            );
                        },
                        () -> customerContainer[0] = Optional.empty()
                );
        return customerContainer[0];
    }

    @Override
    public boolean deleteById(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchById(UUID id, CustomerDTO customer) {
        Optional<Customer> optionalExisting = customerRepository.findById(id);
        if (optionalExisting.isEmpty())
            return false;

        Customer existing = optionalExisting.get();

        if (StringUtils.isNotEmpty(customer.getName()))
            existing.setName(customer.getName());
        return true;
    }
}
