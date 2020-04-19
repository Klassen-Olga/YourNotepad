package md.klass.application.validation;

import md.klass.application.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountValidator implements Validator<Account> {

  @Override
  public List<String> validate(Account account) {
    List<String> errors = new ArrayList<>();

    if (account.getUsername().length() < 2 || account.getPassword().length() < 6) {
      if (account.getUsername().length() < 2) {
        errors.add("Username too short");
      }
      if (account.getPassword().length() < 6) {
        errors.add("Password should not be less than 5 symbols");
      }
    }
    if (!account.getPassword().matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{5,}$")) {
      errors.add("The password should contain al least one character and one digit");
    }
    return errors;
  }
}
