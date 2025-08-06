package zh.learn.spring_6_rest_mvc.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import zh.learn.spring_6_rest_mvc.services.BeerService;

@AllArgsConstructor
@Controller
public class BeerController {
    private final BeerService beerService;
}
