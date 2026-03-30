package src.app;

import java.util.List;

import src.model.Book;
import src.model.EBook;
import src.model.Loan;
import src.model.Member;
import src.model.PrintedBook;
import src.repository.BookRepository;
import src.repository.LoanRepository;
import src.repository.MemberRepository;
import src.repository.file.FileBookRepository;
import src.repository.file.FileMemberRepository;
import src.repository.memory.InMemoryBookRepository;
import src.repository.memory.InMemoryMemberRepository;
import src.repository.memory.InMemoryLoansRepository;
import src.service.LibraryService;
import src.repository.file.FileLoanRepository;

public class Main {
    public static void main(String[] args) {

        // BookRepository bookRepo = new FileBookRepository();
        // MemberRepository memberRepo = new FileMemberRepository();
        // LoanRepository loanRepo = new FileLoanRepository(bookRepo, memberRepo);
        // LibraryService service = new LibraryService(bookRepo, memberRepo, loanRepo);

        // try {
        // System.out.println("___ Books in repository ___");
        // for (Book b : bookRepo.findAll()) {
        // System.out.println(b.getInfo());
        // System.out.println("----------------");
        // }

        // System.out.println("\n___ Members in repository ___");
        // for (Member m : memberRepo.findAll()) {
        // System.out.println(m.getInfo());
        // System.out.println("----------------");
        // }

        // // Loan testing
        // System.out.println("\n___ Loan book (bookID=1, memberID=1) ___");
        // service.loanBook(1, 1);

        // // Show active loans
        // System.out.println("\n___ Loans in repository ___");
        // List<Loan> loans = loanRepo.findAll();
        // for (Loan l : loans) {
        // System.out.println(l.getInfo());
        // System.out.println("----------------");
        // }

        // // Return the book
        // System.out.println("\n___ Return book (bookID = 1) ___");
        // service.returnBook(1);

        // // Show loans after return
        // System.out.println("\n___ Loans after return ___");
        // loans = loanRepo.findAll();
        // for (Loan l : loans) {
        // System.out.println(l.getInfo());
        // System.out.println("----------------");
        // }

        // // Sshowing the book status after return
        // System.out.println("\n___ Books after return ___");
        // for (Book b : bookRepo.findAll()) {
        // System.out.println(b.getInfo());
        // System.out.println("----------------");
        // }

        // } catch (IllegalStateException ex) {
        // System.out.println("Error: " + ex.getMessage());
        // }

        boolean useMemory = hasArg(args, "--memory");
        // boolean useMemory = hasArg(args, "--file");
        // boolean useMemory = true; // for quick testing without command-line arguments

        BookRepository bookRepo;
        MemberRepository memberRepo;
        LoanRepository loanRepo;

        if (useMemory) {
            System.out.println("--- Running in MEMORY mode ---");
            bookRepo = new InMemoryBookRepository();
            memberRepo = new InMemoryMemberRepository();
            loanRepo = new InMemoryLoansRepository();
        } else {
            System.out.println("--- Running in FILE mode ---");
            bookRepo = new FileBookRepository();
            memberRepo = new FileMemberRepository();
            loanRepo = new FileLoanRepository(bookRepo, memberRepo);
        }

        LibraryService service = new LibraryService(bookRepo, memberRepo, loanRepo);

        seedIfEmpty(bookRepo, memberRepo);

        try {
            System.out.println("\n--- Books BEFORE loan ---");
            printBooks(bookRepo.findAll());

            System.out.println("\n--- Members ---");
            printMembers(memberRepo.findAll());

            System.out.println("\n--- Loan book (bookID=2, memberID=2) ---");
            service.loanBook(2, 2);

            System.out.println("\n--- Loans AFTER loan ---");
            printLoans(loanRepo.findAll());

            System.out.println("\n--- Books AFTER loan ---");
            printBooks(bookRepo.findAll());

            System.out.println("\n--- Return book (bookID=1) ---");
            service.returnBook(2);

            System.out.println("\n--- Loans AFTER return ---");
            printLoans(loanRepo.findAll());

            System.out.println("\n--- Books AFTER return ---");
            printBooks(bookRepo.findAll());

        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // helper method to check if a flag is present in the command-line arguments
    // if the flag is present, we run in memory mode; otherwise, we use file-based
    // repositories
    private static boolean hasArg(String[] args, String flag) {
        for (String a : args) {
            if (a.equalsIgnoreCase(flag))
                return true;
        }
        return false;
    }

    private static void seedIfEmpty(BookRepository bookRepo, MemberRepository memberRepo) {
        if (memberRepo.findAll().isEmpty()) {
            memberRepo.add(new Member(1, "Leopold", "leopold123", 3));
            memberRepo.add(new Member(2, "Juliana", "juliana123", 4));
        }

        if (bookRepo.findAll().isEmpty()) {
            bookRepo.add(new PrintedBook(1, "Death of Ivan Ilyich", "Tolstoy", 123));
            bookRepo.add(new EBook(2, "1984", "Orwell", ".pdf", 456));
            bookRepo.add(new PrintedBook(3, "Beloved", "Toni Morrison", 324));
            bookRepo.add(new EBook(4, "The Metamorphosis", "Franz Kafka", ".epub", 512));
        }
    }

    private static void printBooks(List<Book> books) {
        for (Book b : books) {
            System.out.println(b.getInfo());
            System.out.println("----------------");
        }
    }

    private static void printMembers(List<Member> members) {
        for (Member m : members) {
            System.out.println(m.getInfo());
            System.out.println("----------------");
        }
    }

    private static void printLoans(List<Loan> loans) {
        for (Loan l : loans) {
            System.out.println(l.getInfo());
            System.out.println("----------------");
        }
    }
}
