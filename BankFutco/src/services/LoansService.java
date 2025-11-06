package services;

import java.util.*;
import model.Loans;

public class LoansService implements ILoansService {
    private final Map<String, Loans> storage = new HashMap<>();

    @Override
    public Loans save(Loans loan) {
        if (loan == null) return null;
        String id = UUID.randomUUID().toString();
        storage.put(id, loan);
        return loan;
    }

    @Override
    public Optional<Loans> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Loans> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}