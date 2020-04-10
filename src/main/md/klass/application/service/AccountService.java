package md.klass.application.service;

import md.klass.application.models.Account;
import md.klass.application.repository.AccountRepository;
import md.klass.application.validation.AccountValidator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AccountService extends AbstractService<Account> {


	public AccountService() {
		this.validator = new AccountValidator();
		this.repository = new AccountRepository();
	}

	@Override
	public List<String> validate(Account account) {
		List<String> errors = new ArrayList<>(this.validator.validate(account));

		if (!AccountRepository.isUsernameUnique(account)) {
			errors.add("User with this username already exists");
		}
		return errors;
	}

	@Override
	public List<String> save(Connection connection, Account model) {
		return this.repository.insert(connection, model);
	}

}
