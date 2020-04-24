package md.klass.application.repository;

import md.klass.application.database.DataSource;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest {
  @Test
  public void should_return_user_with_firstname_John() throws SQLException {
    String SQL = "select * from User where firstName='Vladlen'";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      String firstName;
      String lastName;
      int id;
      while (resultSet.next()) {
        id = resultSet.getInt(1);
        firstName = resultSet.getString(2);
        lastName = resultSet.getString(3);

        assertTrue("Vladlen".equals(firstName));
        assertTrue("Popkavi4".equals(lastName));
      }
    }
  }

  @Test
  public void inserts_into_account_and_user() throws SQLException {
    String SQL = "insert into User(firstname, lastname) values('Vladlen', 'Popkavi4')";
    String SQL1 = "insert into Account(username, password, userId) values('myPopka','123',?)";
    try (Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement =
            connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS); ) {
      int userId = 0;
      int accountId = 0;
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      while (resultSet.next()) {
        userId = resultSet.getInt(1);
      }

      PreparedStatement preparedStatement1 =
          connection.prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
      preparedStatement1.setInt(1, userId);
      preparedStatement1.executeUpdate();
      resultSet = preparedStatement1.getGeneratedKeys();
      while (resultSet.next()) {
        accountId = resultSet.getInt(1);
      }
      assertEquals(2, userId);
      assertEquals(2, accountId);
    }
  }
}
