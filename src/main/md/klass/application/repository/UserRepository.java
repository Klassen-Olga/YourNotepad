package md.klass.application.repository;

import md.klass.application.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository extends AbstractRepository<User> {

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

}
