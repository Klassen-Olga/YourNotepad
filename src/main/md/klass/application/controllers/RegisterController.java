package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import md.klass.application.repository.Account;
import md.klass.application.repository.User;

import java.util.ArrayList;

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

	public void signUp(MouseEvent mouseEvent) {
		ArrayList<String> errors =new ArrayList<>();
		User user=new User(firstName.getText(), lastName.getText());
		boolean userFieldsAreValid=user.validate(errors);
		if (!userFieldsAreValid){
			errors.stream().forEach(error->{
				if (errorMessage.getText().length()==0){
					errorMessage.setText(error);
				}
				else{
					errorMessage.setText(errorMessage.getText()+"\n"+error);
				}
			});
			return;
		}
		user.insert();

		Account account=new Account(username.getText(), password.getText(), user.getId());
		account.insert();
		account.validate(errors);

	}
}
