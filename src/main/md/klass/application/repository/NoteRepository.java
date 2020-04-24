package md.klass.application.repository;

import md.klass.application.database.DataSource;
import md.klass.application.mapping.RowMapper;
import md.klass.application.models.Account;
import md.klass.application.models.Note;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository extends AbstractRepository<Note, Integer, String>
    implements RowMapper<Note> {

  public NoteRepository() {
    this.tableName = "Note";
    this.SQLInsert = " insert into Note(title, html, accountId) values(?,?,?); ";
    this.SQLUpdate = " update Note set title=?, html=? where id=?";
  }

  @Override
  public void insertOperation(PreparedStatement preparedStatement, Note note) throws SQLException {

    preparedStatement.setString(1, note.getTitle());
    preparedStatement.setString(2, note.getHtml());
    preparedStatement.setInt(3, note.getAccountId());
    preparedStatement.executeUpdate();
    note.setId(this.getId(preparedStatement));
  }

  @Override
  protected void updateOperation(PreparedStatement preparedStatement, Note model)
      throws SQLException {
    preparedStatement.setString(1, model.getTitle());
    preparedStatement.setString(2, model.getHtml());
    preparedStatement.setInt(3, model.getId());
  }

  @Override
  public Note map(ResultSet resultSet) throws SQLException {
    int id = resultSet.getInt(1);
    String title = resultSet.getString(2);
    String html = resultSet.getString(3);
    int accountId = resultSet.getInt(4);
    String lastUpdatedAt=resultSet.getString(6);
    Timestamp convertedDateAndTime=Timestamp.valueOf(lastUpdatedAt);
    LocalDateTime localDateTime=convertedDateAndTime.toLocalDateTime();
    Note note=new Note(id, title, html, accountId);
    note.setLastUpdateAt(localDateTime);
    return note;
  }

  /**
   * @param id is an id we need to find note, which should be updated
   * @return
   */
  @Override
  public Note findOne(Integer id) throws SQLException {
    String SQL = "select * from Note  where id= ?";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        return this.map(resultSet);
      }
    }
    return null;
  }

  @Override
  public List<Note> findMultiple(String username) throws SQLException {
    String SQL = "select * from Note  where accountId= ?";
    AccountRepository accountRepository = new AccountRepository();
    Account account = accountRepository.findOne(username);
    try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
      preparedStatement.setInt(1, account.getId());
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Note> notes = new ArrayList<>();
      while (resultSet.next()) {
        notes.add(this.map(resultSet));
      }
      return notes;
    }
  }
}
