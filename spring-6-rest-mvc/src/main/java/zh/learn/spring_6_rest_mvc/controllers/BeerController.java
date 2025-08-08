package zh.learn.spring_6_rest_mvc.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zh.learn.spring_6_rest_mvc.model.Beer;
import zh.learn.spring_6_rest_mvc.services.BeerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PatchMapping("/{beerId}")
    public ResponseEntity<Void> updateBeerPatchById(@PathVariable("beerId") UUID id, @RequestBody Beer beer) {
        beerService.patchBeerById(id, beer);

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @PostMapping
    public ResponseEntity<Void> handlePost(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());
        return new ResponseEntity<>(
                headers,
                HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
    }

    @GetMapping
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping(value = "/{beerId}")
    public Beer getBeerById(
            @PathVariable("beerId") UUID beerId
    ) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(beerId);
    }
}
