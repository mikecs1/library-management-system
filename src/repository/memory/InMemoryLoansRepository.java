package src.repository.memory;

import java.util.ArrayList;
import java.util.List;

import src.model.Loan;
import src.repository.LoanRepository;

public class InMemoryLoansRepository implements LoanRepository {

    public List<Loan> loans;

    @Override
    public void add(Loan loan) {
        loans.add(loan);
    }

    @Override
    public List<Loan> findAll() {
        return new ArrayList<>(loans);
    }

    @Override
    public void update(Loan loan) {
        for (int i = 0; i < loans.size(); i++)
            if (loans.get(i).getBookID() == loan.getBookID()
                    && loans.get(i).getMemberID() == loan.getMemberID())
                loans.set(i, loan);
        return;
    }
}
