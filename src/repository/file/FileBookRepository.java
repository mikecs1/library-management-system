package src.repository.file;

import src.repository.BookRepository;
import src.model.Book;
import src.model.EBook;
import src.model.PrintedBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileBookRepository implements BookRepository {

    private final String filePath = "src/data/books.txt";

    @Override
    public void add(Book book) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(serialize(book));
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAll() {

        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                books.add(deserialize(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public Book findByID(int id) {

        for (Book b : findAll()) {
            if (b.getID() == id)
                return b;
        }

        return null;
    }

    @Override
    public void update(Book book) {

        List<Book> books = findAll();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Book b : books) {

                if (b.getID() == book.getID())
                    writer.write(serialize(book));
                else
                    writer.write(serialize(b));

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        List<Book> books = findAll();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Book b : books) {

                if (b.getID() != id) {
                    writer.write(serialize(b));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serialize(Book book) {

        if (book instanceof PrintedBook p)
            return "PRINTED," + p.getID() + "," + p.getTitle() + "," + p.getAuthor() + "," + p.getNoPages();

        if (book instanceof EBook e)
            return "EBOOK," + e.getID() + "," + e.getTitle() + "," + e.getAuthor() + "," + e.getFormatExtension() + ","
                    + e.getSize();

        return "";
    }

    private Book deserialize(String line) {

        String[] parts = line.split(",");

        if (parts[0].equals("PRINTED")) {

            return new PrintedBook(
                    Integer.parseInt(parts[1]),
                    parts[2],
                    parts[3],
                    Integer.parseInt(parts[4]));
        }

        if (parts[0].equals("EBOOK")) {

            return new EBook(
                    Integer.parseInt(parts[1]),
                    parts[2],
                    parts[3],
                    parts[4],
                    Integer.parseInt(parts[5]));
        }

        return null;
    }
}