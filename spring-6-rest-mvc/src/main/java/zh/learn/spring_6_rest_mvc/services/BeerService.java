package zh.learn.spring_6_rest_mvc.services;

import zh.learn.spring_6_rest_mvc.model.Beer;

import java.util.UUID;

public interface BeerService {

    Beer getBeerById(UUID id);
}
