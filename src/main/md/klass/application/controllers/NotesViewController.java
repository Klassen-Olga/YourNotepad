package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import md.klass.application.controllerarguments.NoteArgument;
import md.klass.application.controllerarguments.NotesViewArgument;
import md.klass.application.models.Note;
import md.klass.application.service.NoteService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

public class NotesViewController extends AbstractController<NotesViewArgument> {

    @FXML
    public VBox container;
    @FXML
    public Label yourNotes;
    @FXML
            public ScrollPane scroll;

    String username;
    NoteService noteService;

    public NotesViewController() {
        noteService = new NoteService();
    }

    @Override
    public void setInput(NotesViewArgument argument) {

        username = argument.getUsername();
    }

    /**
     * If user had before login some saved notes, we should print them
     */
    @Override
    public void beforeBegin() {
        try {
            final List<Note> notes = noteService.findAllNotesViaUsername(username);

            if (notes.size() == 0) {
                this.yourNotes.setText("You don't have any note yet");
            }
            int sizeForIteration=notes.size()-1;
            scroll.setFitToWidth(true);
            IntStream.rangeClosed(0, sizeForIteration).mapToObj(i->notes.get(sizeForIteration-i)).forEach(note -> {
                Hyperlink hyperlink = new Hyperlink(note.getTitle());
                hyperlink.getStyleClass().add("noteNode");
                hyperlink.setOnMouseClicked(
                        event -> navigateTo("note", new NoteArgument(this.username, note.getId())));
                VBox.setVgrow(hyperlink, Priority.ALWAYS);
                hyperlink.setMaxWidth(Double.MAX_VALUE);
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
