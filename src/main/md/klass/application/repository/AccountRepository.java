package md.klass.application.repository;

import md.klass.application.database.DataSource;
import md.klass.application.mapping.RowMapper;
import md.klass.application.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountRepository extends AbstractRepository<Account, String, String> implements RowMapper<Account> {


	public AccountRepository() {
		this.tableName = "Account";
		this.SQL = " insert into Account(username, password, userId) values(?,?,?); ";
	}

	@Override
	public void insertOperation(PreparedStatement preparedStatement, Account account) throws SQLException {
		preparedStatement.setString(1, account.getUsername());
		preparedStatement.setString(2, account.getPassword());
		preparedStatement.setInt(3, account.getUserId());
		preparedStatement.executeUpdate();
		account.setId(this.getId(preparedStatement));

	}


	/**
	 * For this class find one will be able only through username attribute
	 *
	 * @return
	 */
	@Override
	public Account findOne(String username) throws SQLException {
		String SQL = "select * from Account  where username= ?";
		try (Connection connection = DataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return this.map(resultSet);
			}

		}
		return null;
	}
	@Override
	public List<Account> findMultiple(String value) {
		//not necessary yet
		return null;
	}

	@Override
	public Account map(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(1);
		String usernameDb = resultSet.getString(2);
		String passwordHash = resultSet.getString(3);
		int userId = resultSet.getInt(4);
		return new Account(id, usernameDb, passwordHash, userId);
	}


}
