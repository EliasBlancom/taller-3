package services;

import java.util.List;
import java.util.Optional;
import model.Loans;

public interface ILoansService {
    Loans save(Loans loan);
    Optional<Loans> findById(String id); // ahora id = loan.getId()
    List<Loans> findAll();
    boolean deleteById(String id);
}
