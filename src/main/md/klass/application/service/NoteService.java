package md.klass.application.service;

import md.klass.application.controllers.AbstractController;
import md.klass.application.models.Note;
import md.klass.application.repository.NoteRepository;
import md.klass.application.validation.NoteValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NoteService extends AbstractService<Note, Integer, String > {

	public NoteService(){
		this.repository=new NoteRepository();
		this.validator=new NoteValidator();
	}
	@Override
	public List<String>validate(Note note){
		return validator.validate(note);
	}
	@Override
	public void save(Note note) throws SQLException {
		repository.insert(note);
	}
	public List<Note> findAllNotesViaUsername(String username) throws SQLException {
		return this.repository.findMultiple(username);
	}
	public Note findNoteViaId(int id) throws SQLException {
		return this.repository.findOne(id);
	}
}
