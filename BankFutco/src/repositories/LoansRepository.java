package repositories;

import java.util.*;
import model.Loans;

public class LoansRepository {
    private final Map<String, Loans> storage = new HashMap<>();

    public Loans save(Loans loan) {
        if (loan == null || loan.getId() == null) {
            throw new IllegalArgumentException("Loan o ID no puede ser null");
        }
        storage.put(loan.getId(), loan);
        return loan;
    }

    public Optional<Loans> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    public List<Loans> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}
