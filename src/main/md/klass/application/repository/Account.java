package md.klass.application.repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account extends BaseModel {
	private String username;
	private String password;
	private int userId;

	public Account(String username, String password, int id){
		this.password=password;
		this.userId=id;
		this.username=username;
		this.SQL=" insert into Account(username, password, userId) values(?,?,?); ";
	}
	@Override
	public void insertOperation(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1, this.username);
		preparedStatement.setString(2, this.password);
		preparedStatement.setInt(3, userId);
		preparedStatement.executeUpdate();
		this.id=this.getId(preparedStatement);

	}
	@Override
	public boolean validate(ArrayList<String> errors){
		if (this.username.length()<2 ||this.password.length()<6){
			if (this.username.length()<2){
				errors.add("Username too short");
			}
			if (this.password.length()<6){
				errors.add("Password should than 5 symbols");
			}
			return false;
		}
		return true;

	}
}
