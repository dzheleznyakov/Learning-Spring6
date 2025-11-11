package zh.learn.spring_6_rest_mvc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zh.learn.spring_6_rest_mvc.model.CustomerDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("John Smith")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Jane Doe")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name("Oscar Wilde")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        this.customerMap.put(customer1.getId(), customer1);
        this.customerMap.put(customer2.getId(), customer2);
        this.customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        log.debug("Get all Customers - in service");
        return List.copyOf(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        log.debug("Get Customer by Id - in service");
        return Optional.of(this.customerMap.get(id));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name(customer.getName())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerById(UUID id, CustomerDTO customer) {
        CustomerDTO existingCustomer = customerMap.get(id);
        existingCustomer.setName(customer.getName());
        existingCustomer.setLastModifiedDate(LocalDateTime.now());

        customerMap.put(id, existingCustomer);
        return Optional.of(existingCustomer);
    }

    @Override
    public boolean deleteById(UUID id) {
        customerMap.remove(id);
        return true;
    }

    @Override
    public boolean patchById(UUID id, CustomerDTO customer) {
        CustomerDTO existing = customerMap.get(id);

        if (StringUtils.hasText(customer.getName())) {
            existing.setName(customer.getName());
        }
        return true;
    }
}
