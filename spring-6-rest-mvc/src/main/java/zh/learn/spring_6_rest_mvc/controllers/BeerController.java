package zh.learn.spring_6_rest_mvc.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import zh.learn.spring_6_rest_mvc.model.Beer;
import zh.learn.spring_6_rest_mvc.services.BeerService;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {
    private final BeerService beerService;

    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id - in controller");
        return beerService.getBeerById(id);
    }
}
