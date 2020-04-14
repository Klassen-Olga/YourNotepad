package md.klass.application.models;

public class User extends AbstractBaseModel{
	private String firstName;
	private String lastName;

	public User(String firstName, String lastName){

		this.firstName=firstName;
		this.lastName=lastName;

	}
	public User(int id, String firstName, String lastName){
		this(firstName, lastName);
		this.id=id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
}
