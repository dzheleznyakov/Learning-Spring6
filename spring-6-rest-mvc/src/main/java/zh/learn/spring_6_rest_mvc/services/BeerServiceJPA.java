package zh.learn.spring_6_rest_mvc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zh.learn.spring_6_rest_mvc.entities.Beer;
import zh.learn.spring_6_rest_mvc.mappers.BeerMapper;
import zh.learn.spring_6_rest_mvc.model.BeerDTO;
import zh.learn.spring_6_rest_mvc.repositories.BeerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .toList();
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return beerRepository.findById(id)
                .map(beerMapper::beerToBeerDto);
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return Optional.of(beer)
                .map(beerMapper::beerDtoToBeer)
                .map(beerRepository::save)
                .map(beerMapper::beerToBeerDto)
                .get();
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer) {
        AtomicReference<Optional<BeerDTO>> atomicReference = new AtomicReference<>();

        beerRepository.findById(id)
                .ifPresentOrElse(foundBeer -> {
                    foundBeer.setBeerName(beer.getBeerName());
                    foundBeer.setBeerStyle(beer.getBeerStyle());
                    foundBeer.setUpc(beer.getUpc());
                    foundBeer.setPrice(beer.getPrice());
                    beerRepository.save(foundBeer);
                    atomicReference.set(Optional.of(
                            beerMapper.beerToBeerDto(beerRepository.save(foundBeer))
                    ));
                }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public boolean deleteById(UUID id) {
        if (beerRepository.existsById(id)) {
            beerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchBeerById(UUID id, BeerDTO beer) {
        Optional<Beer> existingOptional = beerRepository.findById(id);

        if (existingOptional.isEmpty())
            return false;

        Beer existing = existingOptional.get();

        if (StringUtils.hasText(beer.getBeerName())) {
            existing.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existing.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getPrice() != null) {
            existing.setPrice(beer.getPrice());
        }

        if (beer.getQuantityOnHand() != null) {
            existing.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existing.setUpc(beer.getUpc());
        }

        return true;
    }
}
