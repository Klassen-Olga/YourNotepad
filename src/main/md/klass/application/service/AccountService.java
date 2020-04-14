package md.klass.application.service;

import md.klass.application.models.Account;
import md.klass.application.repository.AccountRepository;
import md.klass.application.validation.AccountValidator;

import java.sql.Connection;
import java.util.List;

public class AccountService extends AbstractService<Account> {


	public AccountService() {
		this.validator = new AccountValidator();
		this.repository = new AccountRepository();
	}

	@Override
	public List<String> validate(Account account) {
		return this.validator.validate(account);

	}
	public List<String> validateSignUp(Account account){
		List<String> errors=this.validate(account);
		if (AccountRepository.findAccountViaUsername(account.getUsername())!=null) {
			errors.add("User with this username already exists");
		}
		return errors;
	}

	@Override
	public List<String> save(Connection connection, Account model) {
		return this.repository.insert(connection, model);
	}

	public String getPasswordFromUser(Account account){
		if (AccountRepository.findAccountViaUsername(account.getUsername())!=null){
			return AccountRepository.findAccountViaUsername(account.getUsername()).getPassword();
		}
		else{
			return null;
		}
	}
	public Account getAccountViaUsername(String username){
		return AccountRepository.findAccountViaUsername(username);
	}

}
