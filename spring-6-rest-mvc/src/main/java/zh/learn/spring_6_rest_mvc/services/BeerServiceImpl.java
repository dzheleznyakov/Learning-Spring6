package zh.learn.spring_6_rest_mvc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zh.learn.spring_6_rest_mvc.model.Beer;
import zh.learn.spring_6_rest_mvc.model.BeerStyle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get Beer Id in service was called");

        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12345")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDated(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
