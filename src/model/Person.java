package src.model;

public abstract class Person {
    private int id;
    private String name;
    private String email;

    public Person(int _id, String _name, String _email) {
        this.id = _id;
        this.name = _name;
        this.email = _email;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public abstract String getInfo();

    public String toString() {
        return "ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Email: " + email;
    }
}