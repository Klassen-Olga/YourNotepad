package md.klass.application.validation;

import md.klass.application.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator implements Validator<User> {

  @Override
  public List<String> validate(User user) throws IllegalArgumentException {
    List<String> errors = new ArrayList<>();
    if (user.getFirstName().length() < 2 || user.getLastName().length() < 2) {
      if (user.getFirstName().length() < 2) {
        errors.add("First name too short");
      }
      if (user.getLastName().length() < 2) {
        errors.add("Last name too short");
      }
    }

    return errors;
  }
}
