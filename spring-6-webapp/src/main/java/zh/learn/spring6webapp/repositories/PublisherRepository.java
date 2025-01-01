package zh.learn.spring6webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring6webapp.domain.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
