package md.klass.application.service;

import md.klass.application.models.Account;
import md.klass.application.repository.AccountRepository;
import md.klass.application.validation.AccountValidator;

import java.sql.SQLException;
import java.util.List;

public class AccountService extends AbstractService<Account, String, String> {


	public AccountService() {
		this.validator = new AccountValidator();
		this.repository = new AccountRepository();
	}

	@Override
	public List<String> validate(Account account) {
		return this.validator.validate(account);

	}

	public List<String> validateSignUp(Account account) throws SQLException {
		List<String> errors = this.validate(account);
		Account account1 = repository.findOne(account.getUsername());
		if (account1 != null) {
			errors.add("User with this username already exists");
		}
		return errors;
	}

	@Override
	public void save(Account model) throws SQLException {

		this.repository.insert(model);

	}

	public String getPasswordHashFromUser(Account account) throws SQLException {

		Account account1 = this.repository.findOne(account.getUsername());
		if (account1 == null) {
			return null;
		} else {
			return account1.getPassword();
		}

	}

	public Account getAccountViaUsername(String username) throws SQLException {
		return this.repository.findOne(username);
	}

}
