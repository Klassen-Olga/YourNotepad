package md.klass.application.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Note extends BaseModel {
	private String title;
	String htmlText;
	private int accountId;

	public Note(String title, String htmlText, int accountId) {
		this.title = title;
		this.htmlText=htmlText;
		this.accountId=accountId;
		this.SQL = " insert into Note(title, html, accountId) values(?,?,?); ";
	}
	@Override
	public void insertOperation(PreparedStatement preparedStatement) throws SQLException{
		preparedStatement.setString(1, this.title);
		preparedStatement.setString(2, this.htmlText);
		preparedStatement.setInt(3, this.accountId);
		this.id=this.getId(preparedStatement);

	}
	@Override
	public boolean validate(ArrayList<String> errors){
		if (this.title.length()>200){
				errors.add("Title too long");
			return false;
		}
		return true;
	}
}
