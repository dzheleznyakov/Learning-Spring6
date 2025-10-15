package zh.learn.spring_6_rest_mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zh.learn.spring_6_rest_mvc.entities.Customer;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
