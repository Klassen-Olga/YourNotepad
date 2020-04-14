package md.klass.application.controllers;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import md.klass.application.models.Account;
import md.klass.application.navigation.ControllerArgument;
import md.klass.application.navigation.Navigator;
import md.klass.application.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


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
		List<String>errors= accountService.validate(account);
		if (errors.size()>0){
			printErrors(errors);
			return;
		}
		String passwordHash = accountService.getPasswordFromUser(account);
		if (passwordHash == null) {
			printError("There is no user with this username");
			return;
		}
		if (new BCryptPasswordEncoder().matches(password.getText(), passwordHash)) {
			printError("You have successfully signed in");
			try {
				Navigator.navigateTo("notesView", new ControllerArgument<>(account.getUsername()));
			} catch (IOException e) {
				printError(e.getMessage());
				System.out.println(e.getMessage());
			}
		} else {
			printError("Username or password is wrong");
		}

	}

	@FXML
	private void signup() throws IOException {
		Navigator.navigateTo("register");
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
