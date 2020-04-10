package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class AbstractController {

	@FXML
	Label errorMessage;
	public void printErrors(List<String> errors) {

		errorMessage.setText("");
		errors.forEach(error -> {
			if (errorMessage.getText().length() == 0 || errorMessage.getText().equals("")) {
				printError(error);
			} else {
				errorMessage.setText(errorMessage.getText() + "\n" + error);
			}
		});
	}
	public void printError(String error){
		errorMessage.setText(error);
	}
}
