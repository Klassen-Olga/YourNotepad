package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import md.klass.application.controllerarguments.NoteArgument;
import md.klass.application.controllerarguments.NotesViewArgument;
import md.klass.application.models.Note;
import md.klass.application.service.NoteService;

import java.sql.SQLException;
import java.util.List;

public class NotesViewController extends AbstractController<NotesViewArgument> {

  @FXML public VBox container;
  @FXML public Label yourNotes;

  String username;
  NoteService noteService;

  public NotesViewController() {
    noteService = new NoteService();
  }

  @Override
  public void setInput(NotesViewArgument argument) {

    username = argument.getUsername();
  }

  /** If user had before login some saved notes, we should print them */
  @Override
  public void beforeBegin() {

    List<Note> notes = null;
    try {
      notes = noteService.findAllNotesViaUsername(username);

      if (notes.size() == 0) {
        this.yourNotes.setText("You don't have any note yet");
      }
      notes.forEach(
          note -> {
            Hyperlink hyperlink = new Hyperlink(note.getTitle());
            hyperlink.setOnMouseClicked(
                event -> navigateTo("note", new NoteArgument(this.username, note.getId())));
            container.getChildren().add(hyperlink);
          });
    } catch (SQLException | NullPointerException e) {
      printError("Server does not answer, come back later");
    }
  }

  public void addNewNote(MouseEvent mouseEvent) {
    navigateTo("note", new NoteArgument(this.username));
  }
}
