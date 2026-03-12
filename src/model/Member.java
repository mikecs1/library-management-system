package src.model;

public class Member extends Person {
    private int maxBooks;

    public Member(int _id, String _name, String _email, int _maxBooks) {
        super(_id, _name, _email);
        this.maxBooks = _maxBooks;
    }

    public int getMaxBooks() {
        return this.maxBooks;
    }

    @Override
    public String getInfo() {
        return "Member ID: " + getID() +
                "\nName: " + getName() +
                "\nEmail: " + getEmail() +
                "\nMax Books: " + maxBooks;
    }
}
