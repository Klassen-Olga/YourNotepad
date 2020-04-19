package md.klass.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;
import md.klass.application.controllerarguments.NoteArgument;
import md.klass.application.controllerarguments.NotesViewArgument;
import md.klass.application.models.Account;
import md.klass.application.models.Note;
import md.klass.application.service.AccountService;
import md.klass.application.service.NoteService;

import java.sql.SQLException;

public class NoteController extends AbstractController<NoteArgument> {
  @FXML public HTMLEditor editor;
  @FXML public StackPane stackPane;
  private String username;
  private int noteId;
  NoteService noteService;
  AccountService accountService;
  private Note note;

  public NoteController() {
    noteService = new NoteService();
    accountService = new AccountService();
  }

  /**
   * Note will be either updated or inserted, it decide then NoteService class if id exists it will
   * be updated, otherwise it will be inserted
   */
  public void saveNote(MouseEvent mouseEvent) {
    int newNoteId= this.save();
    // case: user pressed first save then delete or update: we should now, which id does it have
    navigateTo("note", new NoteArgument(this.username,newNoteId));
  }
  private int save(){
    int id=0;
    try {
      String html = editor.getHtmlText();
      Note newNote;
      if (this.noteId != 0) {
        Note oldNote = note;
        newNote = new Note(this.noteId, "Title", html, oldNote.getAccountId());
      } else {
        Account account = accountService.getAccountViaUsername(username);
        newNote = new Note("Title", html, account.getId());
      }
      noteService.save(newNote);
      id= newNote.getId();

    } catch (SQLException e) {
      printError("Server does not answer, come back later");
    }
    return id;
  }

  /**
   * there are 2 cases when we are in NoteController First case: we are in NotesViewController and
   * want to create new note then we will have username in argument only, than we will use this
   * username to insert note in database and vie button BACK will use username to return to
   * NotesViewController
   *
   * <p>Second case: we are in NotesViewController and want to update existed note we click on it
   * and in argument will be noteId(of note we want to open) and username-to have possibility to
   * come back to the NotesViewController to the same user
   *
   * <p>That is why username will be always in argument and noteid only in second case
   */
  @Override
  public void setInput(NoteArgument argument) {
    this.username = argument.getUsername();
    if (argument.getId() != 0) {
      this.noteId = argument.getId();
    }
  }

  /** If noteId is set than before user see note view we should load oldNote from database */
  @Override
  public void beforeBegin() {
    if (noteId != 0) {
      try {
        this.note = noteService.findNoteViaId(noteId);
        editor.setHtmlText(note.getHtml());
      } catch (SQLException e) {
        printError("Server does not answer, come back later");
      }
    }
  }

  /** A backward way from note are is only note view area */
  public void goBackward(MouseEvent mouseEvent) {
    // case new note
    if (isNoteUnsaved()) {
      boolean saveNote= showInfoDialogWithOkAndCancelButton("Your note is unsaved", "Do you want to save the changes in the note?",stackPane );
      if (saveNote){
        this.save();
      }
    }

    navigateTo("notesView", new NotesViewArgument(this.username));


  }
  private boolean isNoteUnsaved(){
    return noteId == 0 && !editor.getHtmlText().isEmpty()
        || noteId != 0 && !note.getHtml().equals(editor.getHtmlText());
  }
  public void delete(MouseEvent mouseEvent) {
    if (this.noteId != 0) {
      try {
        this.noteService.delete(this.noteId);
      } catch (SQLException e) {
        printError("Server does not answer, come back later");
      }
    }
    navigateTo("notesView", new NotesViewArgument(username));
  }
}
