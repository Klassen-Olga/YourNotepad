package md.klass.application.service;

import md.klass.application.controllers.AbstractController;
import md.klass.application.models.Note;
import md.klass.application.repository.NoteRepository;
import md.klass.application.validation.NoteValidator;

import java.sql.Connection;
import java.util.List;

public class NoteService extends AbstractService<Note> {

	public NoteService(){
		this.repository=new NoteRepository();
		this.validator=new NoteValidator();
	}
	@Override
	public List<String>validate(Note note){
		return validator.validate(note);
	}
	@Override
	public List<String> save(Connection connection, Note note){
		return repository.insert(connection, note);
	}
	public List<Note> findAllNotesViaUsername(String username){
		return NoteRepository.findAllNotesViaUsername(username);
	}
	public Note findNoteViaId(int id){
		return NoteRepository.findNoteViaId(id);
	}
}
