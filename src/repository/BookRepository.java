package src.repository;

import java.util.List;
import src.model.Book;

public interface BookRepository {

    void add(Book book);

    Book findByID(int id);

    List<Book> findAll();

    void update(Book book);

    void delete(int id);
}