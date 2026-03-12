package src.model;

public class PrintedBook extends Book {
    private int noPages;

    public PrintedBook(int _id, String _title, String _author, int _noPages) {
        super(_id, _title, _author);
        this.noPages = _noPages;
    }

    @Override
    public String getInfo() {
        return "PrintedBook: " + this.getTitle() + "\n" +
                "Author: " + this.getAuthor() + "\n" +
                "Pages: " + this.noPages + "\n" +
                "Available: " + (this.isAvailable() ? "Yes" : "No");
    }

    public int getNoPages() {
        return this.noPages;
    }

    @Override
    public int getLoanDuration() {
        return 14;
    }
}
