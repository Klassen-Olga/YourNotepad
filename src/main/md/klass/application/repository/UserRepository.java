package md.klass.application.repository;

import md.klass.application.mapping.RowMapper;
import md.klass.application.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends AbstractRepository<User, String, String> implements RowMapper<User> {

	public UserRepository() {
		this.SQL = " insert into User(firstName, lastName) values(?,?);";
		this.tableName = "User";
	}

	@Override
	public void insertOperation(PreparedStatement preparedStatement, User user) throws SQLException {
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.executeUpdate();
		user.setId(this.getId(preparedStatement));
	}

	@Override
	public User findOne(String value) {
		//is not necessary yet
		return null;
	}

	@Override
	public List<User> findMultiple(String value) {
		//is not necessary yet
		return null;
	}

	@Override
	public User map(ResultSet resultSet) throws SQLException {
		int id=resultSet.getInt(1);
		String firstName=resultSet.getString(2);
		String lastName=resultSet.getString(3);
		return new User(id, firstName, lastName);
	}
}
