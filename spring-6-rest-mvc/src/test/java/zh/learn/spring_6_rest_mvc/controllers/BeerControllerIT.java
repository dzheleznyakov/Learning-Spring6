package zh.learn.spring_6_rest_mvc.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import zh.learn.spring_6_rest_mvc.entities.Beer;
import zh.learn.spring_6_rest_mvc.mappers.BeerMapper;
import zh.learn.spring_6_rest_mvc.model.BeerDTO;
import zh.learn.spring_6_rest_mvc.model.BeerStyle;
import zh.learn.spring_6_rest_mvc.repositories.BeerRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Test
    void testListBeers() {
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(4);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void testGetById() {
        Beer beer = beerRepository.findAll().get(0);

        BeerDTO dto = beerController.getBeerById(beer.getId());
        assertThat(dto.getId()).isNotNull();
    }

    @Test
    void testBeerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> beerController.getBeerById(UUID.randomUUID()));
    }

    @Rollback
    @Transactional
    @Test
    void testSaveNewBeer() {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer")
                .build();

        ResponseEntity responseEntity = beerController.handlePost(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] segments = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(segments[4]);

        Beer beer = beerRepository.findById(savedUUID).get();
        assertThat(beer).isNotNull();
    }

    @Rollback
    @Transactional
    @Test
    void testUpdateExistingBeer() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDto(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity responseEntity = beerController.updateById(beer.getId(), beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> beerController.updateById(UUID.randomUUID(), BeerDTO.builder().build()));
    }

    @Transactional
    @Rollback
    @Test
    void testDeleteByIdFound() {
        Beer beer = beerRepository.findAll().get(0);

        ResponseEntity responseEntity = beerController.deleteById(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(beerRepository.findById(beer.getId())).isEmpty();
    }

    @Test
    void testDeleteByIdNotFound() {
        assertThrows(NotFoundException.class, () -> beerController.deleteById(UUID.randomUUID()));
    }

    @Transactional
    @Rollback
    @Test
    void testPatchByIdFullUpdate() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Patched Name")
                .beerStyle(BeerStyle.SAISON)
                .price(new BigDecimal("666.00"))
                .quantityOnHand(666)
                .upc("0987654321")
                .build();

        ResponseEntity<Void> responseEntity = beerController.patchById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer foundBeer = beerRepository.findById(beer.getId()).get();
        assertThat(foundBeer.getBeerName()).isEqualTo(beerDTO.getBeerName());
        assertThat(foundBeer.getBeerStyle()).isEqualTo(beerDTO.getBeerStyle());
        assertThat(foundBeer.getPrice()).isEqualTo(beerDTO.getPrice());
        assertThat(foundBeer.getQuantityOnHand()).isEqualTo(beerDTO.getQuantityOnHand());
        assertThat(foundBeer.getUpc()).isEqualTo(beerDTO.getUpc());
    }

    @Test
    @Transactional
    @Rollback
    void testPatchByIdPartialUpdate() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Patched Name")
                .upc("0987654321")
                .build();

        ResponseEntity<Void> responseEntity = beerController.patchById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer foundBeer = beerRepository.findById(beer.getId()).get();
        assertThat(foundBeer.getBeerName()).isEqualTo(beerDTO.getBeerName());
        assertThat(foundBeer.getUpc()).isEqualTo(beerDTO.getUpc());

        assertThat(foundBeer.getBeerStyle()).isEqualTo(beer.getBeerStyle());
        assertThat(foundBeer.getPrice()).isEqualTo(beer.getPrice());
        assertThat(foundBeer.getQuantityOnHand()).isEqualTo(beer.getQuantityOnHand());
    }

    @Transactional
    @Rollback
    @Test
    void testPatchByIdEmptyUpdate() {
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = BeerDTO.builder()
                .build();

        ResponseEntity<Void> responseEntity = beerController.patchById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Beer foundBeer = beerRepository.findById(beer.getId()).get();
        assertThat(foundBeer.getBeerName()).isEqualTo(beer.getBeerName());
        assertThat(foundBeer.getUpc()).isEqualTo(beer.getUpc());
        assertThat(foundBeer.getBeerStyle()).isEqualTo(beer.getBeerStyle());
        assertThat(foundBeer.getPrice()).isEqualTo(beer.getPrice());
        assertThat(foundBeer.getQuantityOnHand()).isEqualTo(beer.getQuantityOnHand());
    }

    @Test
    void testPatchByIdNotFound() {
        assertThrows(
                NotFoundException.class,
                () -> beerController.patchById(UUID.randomUUID(), BeerDTO.builder().build()));
    }
}