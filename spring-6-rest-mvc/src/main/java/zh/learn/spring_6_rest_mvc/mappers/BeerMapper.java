package zh.learn.spring_6_rest_mvc.mappers;

import org.mapstruct.Mapper;
import zh.learn.spring_6_rest_mvc.entities.Beer;
import zh.learn.spring_6_rest_mvc.model.BeerDTO;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);

}
