package md.klass.application.repository;

import md.klass.application.models.Account;
import md.klass.application.models.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository extends AbstractRepository<Note> {


	public NoteRepository() {
		this.SQL = " insert into Note(title, html, accountId) values(?,?,?); ";
		errors=new ArrayList<>();
	}

	@Override
	public void insertOperation(PreparedStatement preparedStatement, Note note) throws SQLException {

		preparedStatement.setString(1, note.getTitle());
		preparedStatement.setString(2, note.getHtml());
		preparedStatement.setInt(3, note.getAccountId());
		preparedStatement.executeUpdate();
		note.setId(this.getId(preparedStatement));

	}

	public static List<Note> findAllNotesViaUsername(String username) {
		Connection connection = getConnection();
		if (connection != null) {
			String SQL = "select * from Note  where accountId= ?";
			Account account = AccountRepository.findAccountViaUsername(username);
			List<Note> notes = new ArrayList<>();
			if (account == null) {
				return null;
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setInt(1, account.getId());
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					int id = resultSet.getInt(1);
					String title = resultSet.getString(2);
					String html = resultSet.getString(3);
					int accountId = Integer.parseInt(resultSet.getString(4));
					notes.add( new Note(id, title, html, accountId));
				}
				return notes;

			} catch (SQLException e) {
				errors.add(e.getMessage());
			} finally {
				AbstractRepository.closeConnectionAndCommitOrRollback(connection, true);
			}
		} else {
			errors.add("Please come back later, the database server doesn't work");
		}
		return null;
	}
	public static Note findNoteViaId(int id){
		Connection connection = getConnection();
		if (connection != null) {
			String SQL = "select * from Note  where id= ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setInt(1,id);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					return new Note(id, resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
				}

			} catch (SQLException e) {
				errors.add(e.getMessage());
			} finally {
				AbstractRepository.closeConnectionAndCommitOrRollback(connection, true);
			}
		} else {
			errors.add("Please come back later, the database server doesn't work");
		}
		return null;
	}

}
