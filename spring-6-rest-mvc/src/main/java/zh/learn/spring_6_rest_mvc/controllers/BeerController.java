package zh.learn.spring_6_rest_mvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zh.learn.spring_6_rest_mvc.model.Beer;
import zh.learn.spring_6_rest_mvc.services.BeerService;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
public class BeerController {
    private final BeerService beerService;

    @RequestMapping("/api/v1/beer")
    public List<Beer> listBeers() {
        return beerService.listBeers();
    }

    @RequestMapping("/api/v1/beer/{id}")
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(id);
    }
}
