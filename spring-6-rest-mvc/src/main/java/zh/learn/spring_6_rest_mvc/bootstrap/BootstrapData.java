package zh.learn.spring_6_rest_mvc.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zh.learn.spring_6_rest_mvc.entities.Beer;
import zh.learn.spring_6_rest_mvc.entities.Customer;
import zh.learn.spring_6_rest_mvc.model.BeerStyle;
import zh.learn.spring_6_rest_mvc.repositories.BeerRepository;
import zh.learn.spring_6_rest_mvc.repositories.CustomerRepository;

import java.math.BigDecimal;

@Component
public class BootstrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final BeerRepository beerRepository;

    public BootstrapData(CustomerRepository customerRepository, BeerRepository beerRepository) {
        this.customerRepository = customerRepository;
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) {
        bootstrapBeers();
        bootstrapCustomers();
    }

    private void bootstrapCustomers() {
        if (customerRepository.count() > 0) return;

        Customer pp = Customer.builder()
                .name("Peter Parker")
                .build();

        Customer bb = Customer.builder()
                .name("Bruce Banner")
                .build();

        Customer mm = Customer.builder()
                .name("Matt Murdock")
                .build();

        Customer rr = Customer.builder()
                .name("Reed Richards")
                .build();

        Customer ss = Customer.builder()
                .name("Sue Storm")
                .build();

        customerRepository.save(pp);
        customerRepository.save(bb);
        customerRepository.save(mm);
        customerRepository.save(rr);
        customerRepository.save(ss);

        System.out.println("Customers bootstrapped: " + customerRepository.count());
    }

    private void bootstrapBeers() {
        if  (beerRepository.count() > 0) return;

        Beer foundersKBS = Beer.builder()
                .beerName("Founders KBS")
                .beerStyle(BeerStyle.STOUT)
                .upc("713955113941")
                .quantityOnHand(12)
                .price(new BigDecimal("9.99"))
                .build();

        Beer brooklynLager = Beer.builder()
                .beerName("Brooklyn Lager")
                .beerStyle(BeerStyle.LAGER)
                .upc("720970928014")
                .quantityOnHand(40)
                .price(new BigDecimal("4.99"))
                .build();

        Beer sierraNevadaPale = Beer.builder()
                .beerName("Sierra Nevada Pale")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("0071395511394")
                .quantityOnHand(16)
                .price(new BigDecimal("5.99"))
                .build();

        Beer weihenstephanerHefe = Beer.builder()
                .beerName("Weihenstephan Hefe")
                .beerStyle(BeerStyle.WHEAT)
                .upc("4004940026217")
                .quantityOnHand(12)
                .price(new BigDecimal("5.49"))
                .build();

        beerRepository.save(foundersKBS);
        beerRepository.save(brooklynLager);
        beerRepository.save(sierraNevadaPale);
        beerRepository.save(weihenstephanerHefe);

        System.out.println("Beers bootstrapped: " + beerRepository.count());
    }
}
