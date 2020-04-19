package md.klass.application.validation;

import md.klass.application.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteValidator implements Validator<Note> {

  @Override
  public List<String> validate(Note note) throws IllegalArgumentException {
    List<String> errors = new ArrayList<>();
    if (note.getTitle().length() > 200) {
      errors.add("Title too long");
    }
    return errors;
  }
}
