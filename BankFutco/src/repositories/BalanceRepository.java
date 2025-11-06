package repositories;

import java.util.*;
import model.Balance;

public class BalanceRepository {
    private final Map<String, Balance> storage = new HashMap<>();

    public Balance save(Balance balance) {
        if (balance == null || balance.getId() == null) {
            throw new IllegalArgumentException("Balance o ID no puede ser null");
        }
        storage.put(balance.getId(), balance);
        return balance;
    }

    public Optional<Balance> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    public List<Balance> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}
