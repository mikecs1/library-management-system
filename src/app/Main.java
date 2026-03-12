package src.app;

import java.util.List;

import src.model.Book;
import src.model.Loan;
import src.model.Member;
import src.repository.BookRepository;
import src.repository.LoanRepository;
import src.repository.MemberRepository;
import src.repository.file.FileBookRepository;
import src.repository.file.FileMemberRepository;
import src.service.LibraryService;
import src.repository.file.FileLoanRepository;

public class Main {
    public static void main(String[] args) {

        BookRepository bookRepo = new FileBookRepository();
        MemberRepository memberRepo = new FileMemberRepository();
        LoanRepository loanRepo = new FileLoanRepository(bookRepo, memberRepo);
        LibraryService service = new LibraryService(bookRepo, memberRepo, loanRepo);

        try {
            System.out.println("___ Books in repository ___");
            for (Book b : bookRepo.findAll()) {
                System.out.println(b.getInfo());
                System.out.println("----------------");
            }

            System.out.println("\n___ Members in repository ___");
            for (Member m : memberRepo.findAll()) {
                System.out.println(m.getInfo());
                System.out.println("----------------");
            }

            // Loan testing
            System.out.println("\n___ Loan book (bookID=1, memberID=1) ___");
            service.loanBook(1, 1);

            // Show active loans
            System.out.println("\n___ Loans in repository ___");
            List<Loan> loans = loanRepo.findAll();
            for (Loan l : loans) {
                System.out.println(l.getInfo());
                System.out.println("----------------");
            }

            // Return the book
            System.out.println("\n___ Return book (bookID = 1) ___");
            service.returnBook(1);

            // Show loans after return
            System.out.println("\n___ Loans after return ___");
            loans = loanRepo.findAll();
            for (Loan l : loans) {
                System.out.println(l.getInfo());
                System.out.println("----------------");
            }

            // Sshowing the book status after return
            System.out.println("\n___ Books after return ___");
            for (Book b : bookRepo.findAll()) {
                System.out.println(b.getInfo());
                System.out.println("----------------");
            }

        } catch (IllegalStateException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}