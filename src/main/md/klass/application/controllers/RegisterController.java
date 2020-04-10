package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import md.klass.application.models.Account;
import md.klass.application.models.User;
import md.klass.application.repository.AccountRepository;
import md.klass.application.repository.AbstractRepository;
import md.klass.application.repository.UserRepository;
import md.klass.application.service.AbstractService;
import md.klass.application.service.AccountService;
import md.klass.application.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	Label errorMessage;
	private UserService userService;
	private AccountService accountService;
	public RegisterController(){
		this.userService=new UserService();
		this.accountService=new AccountService();
	}


	public void signUp(MouseEvent mouseEvent) {

		List<String> errors=new ArrayList<>();
		User user=new User(firstName.getText(), lastName.getText());
		Account account= new Account(username.getText(), password.getText());


		//Validation
		errors.addAll(userService.validate(user));
		errors.addAll(accountService.validate(account));
		if (errors.size() > 0) {
			printError(errors);
			return;
		}
		account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

		//Database connection stuff
		Connection connection = AbstractService.getConnection();
		if (connection==null){
			errors.add("Please come back later, the database server doesn't work");
			printError(errors);
			return;
		}
		userService.save(connection, user);
		account.setUserId(user.getId());
		accountService.save(connection, account);

		if (errors.size() == 0) {
			AbstractRepository.closeConnectionAndCommitOrRollback(connection, true);
		} else {
			AbstractRepository.closeConnectionAndCommitOrRollback(connection, false);
			printError(errors);
		}

	}

	public void printError(List<String> errors) {

		errorMessage.setText("");
		errors.forEach(error -> {
			if (errorMessage.getText().length() == 0 || errorMessage.getText().equals("")) {
				errorMessage.setText(error);
			} else {
				errorMessage.setText(errorMessage.getText() + "\n" + error);
			}
		});
	}

}
