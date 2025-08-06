package zh.learn.spring_6_rest_mvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import zh.learn.spring_6_rest_mvc.model.Beer;
import zh.learn.spring_6_rest_mvc.services.BeerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Beer beer) {
        Beer savedBeer = beerService.saveNewBeer(beer);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatus.CREATED.value()));
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
