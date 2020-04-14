package md.klass.application.controllers;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import md.klass.application.models.Note;
import md.klass.application.navigation.ControllerArgument;
import md.klass.application.navigation.Navigator;
import md.klass.application.service.NoteService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class NotesViewController extends AbstractController implements Initializable {

	@FXML
	public VBox container;
	@FXML
	public Label yourNotes;
	@FXML
	public Pane bluePane;

	String usernameFromLoginController;
	NoteService noteService;

	public NotesViewController() {
		noteService = new NoteService();
	}


	@Override
	public <T> void setInput(ControllerArgument<T> argument) {

		usernameFromLoginController = argument.getArgument();

	}

	/**
	 * If user had before login some saved notes, we should print them
	 */
	@Override
	public void beforeBegin() {
		if (usernameFromLoginController == null) {
			return;
		}
		List<Note> notes = null;
		try {
			notes = noteService.findAllNotesViaUsername(usernameFromLoginController);
		} catch (SQLException e) {
			printError("Server does not answer, come back later");
		}
		if (notes.size() == 0) {
			this.yourNotes.setText("You don't have any note yet");
		}
		notes.forEach(note -> {
			Hyperlink hyperlink = new Hyperlink(note.getTitle());
			hyperlink.setOnMouseClicked(event -> {
				try {
					Navigator.navigateTo("note", new ControllerArgument<>(note.getId()));
				} catch (IOException e) {
					printError("Server does not answer");
				}
			});
			container.getChildren().add(hyperlink);
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	public void addNewNote(MouseEvent mouseEvent) {
		try{
			Navigator.navigateTo("note", new ControllerArgument<>(usernameFromLoginController));
		}catch (IOException e){
			printError(e.getMessage());
		}
	}
}
