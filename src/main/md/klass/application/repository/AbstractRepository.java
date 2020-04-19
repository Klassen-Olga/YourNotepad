package md.klass.application.repository;

import md.klass.application.database.DataSource;
import md.klass.application.models.AbstractBaseModel;

import java.sql.*;
import java.util.List;

/**
 * All method with signature "nameOperation()" are implemented with template method pattern
 *
 * @param <T> Any child class of abstract class BaseModel
 * @param <A> Any type which is used as parameter for method findOne()
 * @param <B> Any type which is used as parameter for method findMultiple()
 */
public abstract class AbstractRepository<T extends AbstractBaseModel, A, B> {

  protected String SQLInsert;
  protected String SQLUpdate;
  protected String tableName;

  public AbstractRepository() {}

  protected abstract void insertOperation(PreparedStatement preparedStatement, T model)
      throws SQLException;

  protected abstract void updateOperation(PreparedStatement preparedStatement, T model)
      throws SQLException;

  public void insert(T model) throws SQLException {

    try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement(SQLInsert, Statement.RETURN_GENERATED_KEYS)) {
      this.insertOperation(preparedStatement, model);
    }
  }

  public void update(T model) throws SQLException {
    try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLUpdate)) {
      this.updateOperation(preparedStatement, model);
      preparedStatement.executeUpdate();
    }
  }

  public void delete(int id) throws SQLException {
    String SQLDelete = " delete from " + this.tableName + " where id=" + id;
    try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLDelete)) {
      preparedStatement.executeUpdate();
    }
  }

  protected int getId(PreparedStatement preparedStatement) throws SQLException {
    int id = 0;
    try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
      if (rs.next()) {
        id = rs.getInt(1);
      }
    }
    return id;
  }

  public abstract T findOne(A value) throws SQLException;

  public abstract List<T> findMultiple(B value) throws SQLException;
}
