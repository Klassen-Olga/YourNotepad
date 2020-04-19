package md.klass.application.service;

import md.klass.application.models.User;
import md.klass.application.repository.UserRepository;
import md.klass.application.validation.UserValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.util.List;

public class UserService extends AbstractService<User, String, String> {
  private static final Log logger = LogFactory.getLog(UserService.class);

  public UserService() {
    this.validator = new UserValidator();
    this.repository = new UserRepository();
  }

  @Override
  public List<String> validate(User model) {
    return this.validator.validate(model);
  }

  @Override
  public void save(User model) throws SQLException {
    this.repository.insert(model);
  }
}
