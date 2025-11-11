package zh.learn.spring_6_rest_mvc.services;

import zh.learn.spring_6_rest_mvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<BeerDTO> listBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer);

    boolean deleteById(UUID id);

    boolean patchBeerById(UUID id, BeerDTO beer);
}
