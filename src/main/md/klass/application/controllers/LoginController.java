package md.klass.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import md.klass.application.Main;
import md.klass.application.navigation.Navigator;


public class LoginController {
	@FXML
	public Button button;

	@FXML
	public TextField username;


	@FXML
	private void login(ActionEvent event) {

		if (username.getText().length()>0){
			button.setText(username.getText());
		}
		else button.setText("You've clicked!");

	}
	@FXML
	private void signup()throws Exception{
		Navigator.navigateTo("register");
	}
}
