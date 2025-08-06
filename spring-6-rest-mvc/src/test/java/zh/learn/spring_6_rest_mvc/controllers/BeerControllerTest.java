package zh.learn.spring_6_rest_mvc.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @Test
    void getBeeriById() {
        System.out.println(beerController.getBeerById(UUID.randomUUID()));
    }

}