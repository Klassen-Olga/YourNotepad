package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import md.klass.application.controllerarguments.RegisterArgument;
import md.klass.application.models.Account;
import md.klass.application.models.User;
import md.klass.application.navigation.Navigator;
import md.klass.application.service.AccountService;
import md.klass.application.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController extends AbstractController<RegisterArgument> {
  @FXML TextField firstName;
  @FXML TextField lastName;
  @FXML TextField username;
  @FXML TextField password;
  @FXML VBox vtext;

  private UserService userService;
  private AccountService accountService;

  public RegisterController() {
    this.userService = new UserService();
    this.accountService = new AccountService();
  }

  @FXML
  public void initialize() {
    vtext.setDisable(false);
    vtext.requestFocus();
  }

  public void signUp(MouseEvent mouseEvent) {

    try {
      List<String> errors = new ArrayList<>();
      User user = new User(firstName.getText(), lastName.getText());
      Account account = new Account(username.getText(), password.getText());

      // Validation
      errors.addAll(userService.validate(user));
      errors.addAll(accountService.validateSignUp(account));
      if (errors.size() > 0) {
        printErrors(errors);
        return;
      }
      account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

      // Database connection stuff
      userService.save(user);
      account.setUserId(user.getId());
      accountService.save(account);
      Navigator.navigateTo("login");
    } catch (SQLException | IOException e1) {
      printError("Server does not answer, come back later");
    }
  }

  @Override
  public void setInput(RegisterArgument argument) {
    // is not necessary
  }

  @Override
  public void beforeBegin() {
    // is not necessary
  }
}
