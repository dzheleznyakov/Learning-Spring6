package zh.learn.spring6webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import zh.learn.spring6webapp.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
