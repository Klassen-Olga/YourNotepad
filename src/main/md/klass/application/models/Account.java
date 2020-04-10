package md.klass.application.models;

public class Account extends AbstractBaseModel {
	private String username;
	private String password;
	private int userId;


	public Account(String username, String password){
		this.password=password;
		this.username=username;
	}
	public void setUserId(int userId){
		this.userId=userId;
	}
	public int getUserId(){ return this.userId;}
	public void setPassword(String password){this.password=password;}
	public String getPassword(){return this.password;}
	public String getUsername() {
		return this.username;
	}
}
