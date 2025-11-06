package services;

import java.util.List;
import java.util.Optional;
import model.Loans;
import repositories.LoansRepository;

public class LoansService implements ILoansService {
    private final LoansRepository repository;

    public LoansService() {
        this(new LoansRepository());
    }

    public LoansService(LoansRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loans save(Loans loan) {
        if (loan == null) {
            throw new IllegalArgumentException("Loan no puede ser null");
        }
        return repository.save(loan);
    }

    @Override
    public Optional<Loans> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Loans> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        return repository.deleteById(id);
    }
}