package md.klass.application.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends BaseModel{

	private String firstName;
	private String lastName;

	public User(String firstName, String lastName){
		this.SQL=" insert into User(firstName, lastName) values(?,?);";
		this.firstName=firstName;
		this.lastName=lastName;

	}
	@Override
	public void insertOperation(PreparedStatement preparedStatement)throws SQLException {
		preparedStatement.setString(1, this.firstName);
		preparedStatement.setString(2,this.lastName);
		preparedStatement.executeUpdate();
		this.id=this.getId(preparedStatement);
	}
	@Override
	public boolean validate(ArrayList<String> errors){
		if (this.firstName.length()<2 ||this.lastName.length()<6){
			if (this.firstName.length()<2){
				errors.add("Username too short");
			}
			if (this.lastName.length()<2){
				errors.add("Password should than 5 symbols");
			}
			return false;
		}
		return true;

	}
}
