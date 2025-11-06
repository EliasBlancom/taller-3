package services;

import java.util.List;
import java.util.Optional;
import model.Account;

public interface IAccountService {
	Account save(Account account);
	Optional<Account> findById(String accountNumber);
	List<Account> findAll();
	boolean deleteById(String accountNumber);
}
