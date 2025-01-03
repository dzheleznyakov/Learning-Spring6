package zh.learn.spring6webapp.services;

import zh.learn.spring6webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
