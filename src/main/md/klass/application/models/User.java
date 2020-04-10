package md.klass.application.models;

public class User extends AbstractBaseModel{
	private String firstName;
	private String lastName;

	public User(String firstName, String lastName){

		this.firstName=firstName;
		this.lastName=lastName;

	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
}
