package md.klass.application.service;

import md.klass.application.models.Note;
import md.klass.application.repository.NoteRepository;
import md.klass.application.validation.NoteValidator;

import java.sql.SQLException;
import java.util.List;

public class NoteService extends AbstractService<Note, Integer, String> {

  public NoteService() {
    this.repository = new NoteRepository();
    this.validator = new NoteValidator();
  }

  @Override
  public List<String> validate(Note note) {
    return validator.validate(note);
  }

  @Override
  public void save(Note note) throws SQLException {
    if (note.getId() == 0) {
      repository.insert(note);
    } else {
      repository.update(note);
    }
  }

  public List<Note> findAllNotesViaUsername(String username) throws SQLException {
    return this.repository.findMultiple(username);
  }

  public Note findNoteViaId(int id) throws SQLException {
    return this.repository.findOne(id);
  }

  public void delete(int id) throws SQLException {
    this.repository.delete(id);
  }
}
