package zh.learn.spring_6_rest_mvc.services;

import zh.learn.spring_6_rest_mvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomerById(UUID id);

    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID id, Customer customer);

    void deleteById(UUID id);

    void patchById(UUID id, Customer customer);
}
