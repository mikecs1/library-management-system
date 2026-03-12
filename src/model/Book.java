package src.model;

public abstract class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int _id, String _title, String _author) {
        this.id = _id;
        this.title = _title;
        this.author = _author;
        this.available = true; // default value for availability is true
    }

    public int getID() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean value) {
        this.available = value;
    }

    public abstract String getInfo();

    public abstract int getLoanDuration();
}
