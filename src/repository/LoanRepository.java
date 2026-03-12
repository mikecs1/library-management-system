package src.repository;

import java.util.List;
import src.model.Loan;

public interface LoanRepository {
    void add(Loan loan);

    List<Loan> findAll();

    void update(Loan loan);
}