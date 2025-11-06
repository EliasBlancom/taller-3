package services;

import java.util.List;
import java.util.Optional;
import model.Balance;
import repositories.BalanceRepository;

public class BalanceService implements IBalanceService {
    private final BalanceRepository repository;

    public BalanceService() {
        this(new BalanceRepository());
    }

    public BalanceService(BalanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Balance save(Balance balance) {
        if (balance == null) {
            throw new IllegalArgumentException("Balance no puede ser null");
        }
        return repository.save(balance);
    }

    @Override
    public Optional<Balance> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Balance> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        return repository.deleteById(id);
    }
}