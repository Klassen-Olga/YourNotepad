package md.klass.application.repository;

import md.klass.application.models.AbstractBaseModel;
import md.klass.application.models.Note;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoteRepository extends AbstractRepository {


	public NoteRepository() {
		this.SQL = " insert into Note(title, html, accountId) values(?,?,?); ";
	}

	@Override
	public void insertOperation(PreparedStatement preparedStatement, AbstractBaseModel model) throws SQLException, IllegalArgumentException{
		if (model instanceof Note){
			Note note=(Note)model;
			preparedStatement.setString(1, note.getTitle());
			preparedStatement.setString(2, note.getHtml());
			preparedStatement.setInt(3, note.getAccountId());
			model.setId(this.getId(preparedStatement));
		}
		else{
			throw new  IllegalArgumentException();
		}

	}

}
