package services;

import java.util.*;
import model.Balance;

public class BalanceService implements IBalanceService {
    private final Map<String, Balance> storage = new HashMap<>();

    @Override
    public Balance save(Balance balance) {
        if (balance == null) return null;
        String id = UUID.randomUUID().toString();
        storage.put(id, balance);
        return balance;
    }

    @Override
    public Optional<Balance> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Balance> findAll() {
        return new ArrayList<>(storage.values());
    }

   @Override
    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }
}