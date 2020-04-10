package md.klass.application.repository;

import md.klass.application.models.AbstractBaseModel;
import md.klass.application.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository extends AbstractRepository<Account> {
	private static List<String> errors;

	public AccountRepository(){
		this.tableName="Account";
		this.SQL=" insert into Account(username, password, userId) values(?,?,?); ";
		errors=new ArrayList<>();
	}
	@Override
	public void insertOperation(PreparedStatement preparedStatement, Account account) throws SQLException, IllegalArgumentException {
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setInt(3, account.getUserId());
			preparedStatement.executeUpdate();
			account.setId(this.getId(preparedStatement));

	}

	public static boolean isUsernameUnique( Account account){
		ResultSet resultSet=null;
		Connection connection=getConnection();

		if (connection!=null){
			String SQL="select * from Account  where username= ?";
			try(PreparedStatement preparedStatement= connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
				preparedStatement.setString(1,account.getUsername());
				resultSet=preparedStatement.executeQuery();
				if (!resultSet.next()){
					return true;
				}
			}catch (SQLException e){
				errors.add(e.getMessage());
			}
			finally {
				AbstractRepository.closeConnectionAndCommitOrRollback(connection, true);
			}
		}
		else{
			errors.add("Please come back later, the database server doesn't work");

		}
		return false;
	}
	public static String getPasswordFromUser(Account account){
		Connection connection=getConnection();
		if (connection!=null){
			String SQL="select * from Account  where username= '"+account.getUsername()+"'";
			try(PreparedStatement preparedStatement= connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
				ResultSet resultSet=preparedStatement.executeQuery();
				if (!resultSet.next()){
					return null;
				}
				else{
					return resultSet.getString(3);
				}
			}catch (SQLException e){
				errors.add(e.getMessage());
			}
			finally {
				AbstractRepository.closeConnectionAndCommitOrRollback(connection, true);
			}
		}
		else{
			errors.add("Please come back later, the database server doesn't work");
		}
		return null;
	}
}
