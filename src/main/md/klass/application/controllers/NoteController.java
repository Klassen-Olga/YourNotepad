package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class NoteController {
	@FXML
	public HTMLEditor editor;
	public WebView noteWebview;


	public void saveNote(MouseEvent mouseEvent) {

		WebEngine webEngine=noteWebview.getEngine();
		webEngine.loadContent(editor.getHtmlText());
	}
}
