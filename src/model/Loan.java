package src.model;

import java.time.LocalDate;

public class Loan {
    private Book book;
    private Member member;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(book.getLoanDuration());
        this.returnDate = null;

        book.setAvailable(false);
    }

    public Member getMember() {
        return this.member;
    }

    public Book getBook() {
        return this.book;
    }

    public int getBookID() {
        return this.book.getID();
    }

    public int getMemberID() {
        return this.member.getID();
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        book.setAvailable(true);
    }

    public String getInfo() {
        return "Book: " + book.getTitle() +
                "\nMember: " + member.getName() +
                "\nLoan Date: " + loanDate +
                "\nDue Date: " + dueDate +
                "\nReturn Date: " + (returnDate != null ? returnDate : "Not returned yet");
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
