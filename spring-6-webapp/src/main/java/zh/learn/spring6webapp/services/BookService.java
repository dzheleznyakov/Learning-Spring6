package zh.learn.spring6webapp.services;

import zh.learn.spring6webapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
