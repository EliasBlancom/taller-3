package services;

import java.util.List;
import java.util.Optional;
import model.Account;
import repositories.AccountRepository;

public class AccountService implements IAccountService {
	private final AccountRepository repository;

	// Constructor por defecto: crea e inyecta el repositorio
	public AccountService() {
		this(new AccountRepository());
	}

	// Constructor para inyección
	public AccountService(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public Account save(Account account) {
		// Delegamos al repositorio (upsert). El repositorio usa un Map para garantizar unicidad por accountNumber.
		return repository.save(account);
	}

	@Override
	public Optional<Account> findById(String accountNumber) {
		return repository.findById(accountNumber);
	}

	@Override
	public List<Account> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean deleteById(String accountNumber) {
		return repository.deleteById(accountNumber);
	}
}
