package md.klass.application.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import md.klass.application.models.Account;
import md.klass.application.models.Note;
import md.klass.application.navigation.ControllerArgument;
import md.klass.application.repository.AbstractRepository;
import md.klass.application.service.AccountService;
import md.klass.application.service.NoteService;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class NoteController extends  AbstractController implements Initializable {
	@FXML
	public HTMLEditor editor;

	public WebView noteWebview;
	private String username;
	private Integer noteId;
	NoteService noteService;
	AccountService accountService;

	public NoteController(){
		noteService=new NoteService();
		accountService=new AccountService();
	}


	public void showNote(MouseEvent mouseEvent) {

		WebEngine webEngine=noteWebview.getEngine();
		webEngine.loadContent(editor.getHtmlText());
	}
	public void saveNote(MouseEvent mouseEvent) {
		try{
			String html=editor.getHtmlText();
			Account account = accountService.getAccountViaUsername(username);
			int id=account.getId();
			Note note=new Note("Title", html,account.getId());
			noteService.save(note);
		}catch (SQLException e){
			printError("Server does not answer, come back later");
		}

	}

	/**
	 * @param argument is integer if user had clicked on existed note and wants update it
	 *                 or it is String and user wants to create a new note
	 */
	@Override
	public <T> void setInput(ControllerArgument<T> argument) {
		if (argument.getArgument() instanceof String){
			this.username=argument.getArgument();
		}
		else if (argument.getArgument() instanceof Integer){
			this.noteId=argument.getArgument();
		}
	}

	@Override
	public void beforeBegin() {
		if (noteId!=null){
			try{
				Note note =noteService.findNoteViaId(noteId);
				editor.setHtmlText(note.getHtml());
			}catch (SQLException e){
				printError("Server does not answer, come back later");
			}

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}
}
