package src.model;

public class EBook extends Book {
    private String formatExtention;
    private int size;

    public EBook(int id, String title, String author, String formatExtension, int size) {
        super(id, title, author);
        this.formatExtention = formatExtension;
        this.size = size;
    }

    public String getFormatExtension() {
        return this.formatExtention;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String getInfo() {
        return "EBook: " + this.getTitle() + "\n" +
                "Author: " + this.getAuthor() + "\n" +
                "Format: " + this.formatExtention + "\n" +
                "Size: " + this.size + "KB" + "\n" +
                "Available: " + (this.isAvailable() ? "Yes" : "No");
    }

    @Override
    public int getLoanDuration() {
        return 7;
    }
}
