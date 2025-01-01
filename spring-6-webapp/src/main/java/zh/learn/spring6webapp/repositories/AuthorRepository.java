package zh.learn.spring6webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring6webapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
