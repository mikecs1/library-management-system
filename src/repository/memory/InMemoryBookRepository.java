package src.repository.memory;

import java.util.ArrayList;
import java.util.List;
import src.repository.BookRepository;

import src.model.Book;

public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public Book findByID(int id) {
        for (Book b : books)
            if (b.getID() == id)
                return b;
        return null;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public void update(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID() == book.getID()) {
                books.set(i, book);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        books.removeIf(b -> b.getID() == id);
    }

}
