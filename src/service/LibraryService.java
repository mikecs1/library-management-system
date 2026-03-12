package src.service;

import src.repository.BookRepository;
import src.repository.LoanRepository;
import src.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import src.model.Book;
import src.model.Loan;
import src.model.Member;

public class LibraryService {
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private LoanRepository loanRepository;

    public LibraryService(BookRepository bookRepository, MemberRepository memberRepository,
            LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    public void loanBook(int bookID, int memberID) {
        Book b = bookRepository.findByID(bookID);
        Member m = memberRepository.findByID(memberID);

        if (b == null)
            throw new IllegalArgumentException("Book not found");
        if (m == null)
            throw new IllegalArgumentException("Member not found");
        if (!b.isAvailable())
            throw new IllegalArgumentException("Book is not available for renting");

        Loan l = new Loan(b, m);

        LocalDate actualTime = LocalDate.now();

        l.setLoanDate(actualTime);
        l.setDueDate(actualTime.plusDays(b.getLoanDuration()));
        l.setReturnDate(null);

        loanRepository.add(l);
        b.setAvailable(false);
        bookRepository.update(b);
    }

    public void returnBook(int bookID) {

        List<Loan> loans = new ArrayList<>();
        loans = loanRepository.findAll();

        for (Loan l : loans) {
            if (l.getBook().getID() == bookID && l.getReturnDate() == null) {
                l.setReturnDate(LocalDate.now());
                Book b = l.getBook();
                b.setAvailable(true);
                loanRepository.update(l);
                bookRepository.update(b);
                return;
            }

        }
        throw new IllegalArgumentException("Active loan not found");
    }

}
