package src.repository.file;

import src.repository.BookRepository;
import src.repository.LoanRepository;
import src.repository.MemberRepository;
import src.model.Loan;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import src.model.Book;
import src.model.Member;

public class FileLoanRepository implements LoanRepository {

    private final String filePath = "src/data/loans.txt";

    private BookRepository bookRepository;
    private MemberRepository memberRepository;

    public FileLoanRepository(BookRepository bookRepo, MemberRepository memberRepo) {
        this.bookRepository = bookRepo;
        this.memberRepository = memberRepo;
    }

    @Override
    public void add(Loan l) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(serialize(l));
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Loan> findAll() {
        List<Loan> loans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null)
                loans.add(deserialize(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void update(Loan loan) {
        List<Loan> loans = findAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Loan l : loans) {
                if (l.getBook().getID() == loan.getBook().getID()
                        && l.getMember().getID() == loan.getMember().getID()) {
                    writer.write(serialize(loan));
                } else
                    writer.write(serialize(l));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error updating loan", e);
        }
    }

    public String serialize(Loan l) {
        return l.getBook().getID() + "," + l.getMember().getID() + "," + l.getLoanDate() + "," + l.getDueDate() + ","
                + (l.getReturnDate() != null ? l.getReturnDate() : "null");
    }

    public Loan deserialize(String line) {
        String[] parts = line.split(",");
        int bookID = Integer.parseInt(parts[0]);
        int memberID = Integer.parseInt(parts[1]);
        LocalDate loanDate = parts[2].equals("null") ? null : LocalDate.parse(parts[2]);
        LocalDate dueDate = parts[3].equals("null") ? null : LocalDate.parse(parts[3]);
        LocalDate returnDate = parts[4].equals("null") ? null : LocalDate.parse(parts[4]);

        Book book = bookRepository.findByID(bookID);
        Member member = memberRepository.findByID(memberID);

        Loan loan = new Loan(book, member);
        loan.setLoanDate(loanDate);
        loan.setDueDate(dueDate);
        loan.setReturnDate(returnDate);
        return loan;
    }
}
