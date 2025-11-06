package services;

import java.util.List;
import java.util.Optional;
import model.Balance;

public interface IBalanceService {
	Balance save(Balance balance);
	Optional<Balance> findById(String id); // id = date.toString()
	List<Balance> findAll();
	boolean deleteById(String id);
}
