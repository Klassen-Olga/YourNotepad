package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import md.klass.application.navigation.ControllerArgument;

import java.util.List;

public abstract class AbstractController {

	public abstract <T> void setInput(ControllerArgument<T> argument);
	public abstract void beforeBegin();
	@FXML
	Label errorMessage;

	public void printErrors(List<String> errors) {
		errorMessage.setText("");
		if (errors.size()<2){
			printError(errors.get(0));
			return;

		}
		errors.subList(0,2).forEach(error -> {
			if (errorMessage.getText().length() == 0 || errorMessage.getText().equals("")) {
				printError(error);
			} else {
				errorMessage.setText(errorMessage.getText() + "\n" + error);
			}
		});
	}

	public void printError(String error) {
		errorMessage.setText(error);
	}
}
