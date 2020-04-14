package md.klass.application.repository;

import md.klass.application.models.AbstractBaseModel;
import md.klass.application.models.Account;
import md.klass.application.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository extends AbstractRepository<Account> {


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

	public static Account findAccountViaUsername(String username){
		Connection connection=getConnection();
		if (connection!=null){
			String SQL="select * from Account  where username= ?";
			try(PreparedStatement preparedStatement= connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
				preparedStatement.setString(1,username);
				ResultSet resultSet=preparedStatement.executeQuery();
				//ResultSet to entity
				if (!resultSet.next()){
					return null;
				}
				else{
					int id=resultSet.getInt(1);
					String usernameDb=resultSet.getString(2);
					String passwordHash=resultSet.getString(3);
					int accountId=Integer.parseInt(resultSet.getString(4));
					return new Account(id,usernameDb, passwordHash,accountId);
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
