package services;

import java.util.List;
import java.util.Optional;
import model.Balance;

public interface IBalanceService {
    Balance save(Balance balance);
    Optional<Balance> findById(String id); // ahora id = balance.getId()
    List<Balance> findAll();
    boolean deleteById(String id);
}
