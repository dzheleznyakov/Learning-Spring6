package zh.learn.spring_6_rest_mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zh.learn.spring_6_rest_mvc.entities.Beer;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
