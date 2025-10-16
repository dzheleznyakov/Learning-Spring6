package zh.learn.spring_6_rest_mvc.bootstrap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zh.learn.spring_6_rest_mvc.entities.Beer;
import zh.learn.spring_6_rest_mvc.entities.Customer;
import zh.learn.spring_6_rest_mvc.repositories.BeerRepository;
import zh.learn.spring_6_rest_mvc.repositories.CustomerRepository;

@DataJpaTest
class BootstrapDataTest {

    @Autowired
    BeerRepository beerRepository;
    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        new BootstrapData(beerRepository, customerRepository).run();
    }

    @Test
    void testBootstrapBeerData() {
        Assertions.assertThat(beerRepository.count()).isEqualTo(4);
        for (Beer beer : beerRepository.findAll()) {
            Assertions.assertThat(beer.getId()).isNotNull();
            Assertions.assertThat(beer.getVersion()).isNotNull();
            Assertions.assertThat(beer.getBeerName()).isNotNull();
        }
    }

    @Test
    void testBootstrapCustomerData() {
        Assertions.assertThat(customerRepository.count()).isEqualTo(5);
        for (Customer customer : customerRepository.findAll()) {
            Assertions.assertThat(customer.getId()).isNotNull();
            Assertions.assertThat(customer.getVersion()).isNotNull();
            Assertions.assertThat(customer.getName()).isNotNull();
        }
    }
}