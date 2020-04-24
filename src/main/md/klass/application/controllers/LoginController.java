package md.klass.application.controllers;

/*import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import md.klass.application.controllerarguments.LoginArgument;
import md.klass.application.controllerarguments.NotesViewArgument;
import md.klass.application.models.Account;
import md.klass.application.navigation.Navigator;
import md.klass.application.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LoginController extends AbstractController<LoginArgument> {

	@FXML
	public Button button;
	@FXML
	public TextField username;
	@FXML
	public TextField password;
	@FXML
	public StackPane bp;

	public AccountService accountService;

	public LoginController() {
		accountService = new AccountService();
	}

	void uploadFile() throws IOException{
		final FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(Navigator.getStage());
		FileWriter fw = new FileWriter(file);



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
				navigateTo("notesView", new NotesViewArgument(account.getUsername()));
			} else {
				printError("Username or password is wrong");
			}

		} catch (SQLException  e) {
			printError("Server does not answer, come back later");
		}

	}

	@FXML
	private void signup() throws IOException {
		navigateTo("register");
	}

	@Override
	public void setInput(LoginArgument argument) {
		//is not necessary yet
	}


	@Override
	public void beforeBegin() {
		//is not necessary yet

	}


}
