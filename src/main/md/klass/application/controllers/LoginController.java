package md.klass.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import md.klass.application.models.Account;
import md.klass.application.navigation.Navigator;
import md.klass.application.service.AccountService;
import md.klass.application.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class LoginController extends  AbstractController{

	@FXML
	public Button button;
	@FXML
	public TextField username;
	@FXML
	public TextField password;

	public AccountService accountService;

	public LoginController() {
		accountService=new AccountService();
	}

	@FXML
	private void login(ActionEvent event) {

		Account account=new Account(username.getText(), password.getText());

		String passwordHash=accountService.getPasswordFromUser(account);
		if (password==null){
			printError("User with such username doesn't exist");
			return;
		}
		else{
			if (new BCryptPasswordEncoder().matches(password.getText(), passwordHash)){
				printError("You have successfully signed in");
			}
			else{
				printError("Username or password is wrong");
			}
		}

	}

	@FXML
	private void signup() throws Exception {
		Navigator.navigateTo("register");
	}
}
