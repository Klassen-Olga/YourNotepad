package md.klass.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import md.klass.application.models.Account;
import md.klass.application.navigation.ControllerArgument;
import md.klass.application.navigation.Navigator;
import md.klass.application.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LoginController extends AbstractController {

	@FXML
	public Button button;
	@FXML
	public TextField username;
	@FXML
	public TextField password;


	public AccountService accountService;

	public LoginController() {
		accountService = new AccountService();
	}

	@FXML
	private void login(ActionEvent event) {

		Account account = new Account(username.getText(), password.getText());
		List<String> errors = accountService.validate(account);
		if (errors.size() > 0) {
			printErrors(errors);
			return;
		}
		try {
			String passwordHash = accountService.getPasswordHashFromUser(account);
			if (passwordHash == null) {
				printError("There is no user with this username");
				return;
			}
			if (new BCryptPasswordEncoder().matches(password.getText(), passwordHash)) {
				Navigator.navigateTo("notesView", new ControllerArgument<>(account.getUsername()));
			} else {
				printError("Username or password is wrong");
			}

		} catch (SQLException | IOException e) {
			printError("Server does not answer, come back later");
		}

	}

	@FXML
	private void signup() throws IOException {
		try {
			Navigator.navigateTo("register");
		}catch (IOException e){
			printError("Server does not answer, come back later");
		}
	}

	@Override
	public <T> void setInput(ControllerArgument<T> argument) {
		String a = argument.getArgument();
	}

	@Override
	public void beforeBegin() {

	}


	public void foo(MouseEvent mouseEvent) {
	}
}
