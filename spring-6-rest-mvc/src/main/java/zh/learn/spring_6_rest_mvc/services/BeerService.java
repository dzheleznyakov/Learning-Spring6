package zh.learn.spring_6_rest_mvc.services;

import zh.learn.spring_6_rest_mvc.model.Beer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    List<Beer> listBeers();

    Optional<Beer> getBeerById(UUID id);

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID id, Beer beer);

    void deleteById(UUID id);

    void patchBeerById(UUID id, Beer beer);
}
