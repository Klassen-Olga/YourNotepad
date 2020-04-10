package md.klass.application.repository;


import md.klass.application.models.AbstractBaseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractBaseModel> {

	protected String SQL;
	protected static String tableName;
	private static List<String> errors;
	public AbstractRepository(){
		errors=new ArrayList<>();
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/YourNotepad?useSSL=false", "debian-sys-maint", "G65C1J4iIxMIxVgF");
			connection.setAutoCommit(false);
			return connection;

		} catch (SQLException e) {
			return null;
		}
	}

	protected abstract void insertOperation(PreparedStatement preparedStatement, T model) throws SQLException;

	public List<String> insert(Connection connection, T model) {
		if (connection!=null){
			try (PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

				this.insertOperation(preparedStatement, model);


			} catch (SQLException e) {
				errors.add(e.getMessage());
			}

		}
		return errors;

	}

	/**
	 * @param commit if is true we commit changes, otherwise rollback
	 */
	public static List<String> closeConnectionAndCommitOrRollback(Connection connection, boolean commit) {
		if (connection != null) {
			try {
				if (commit){
					connection.commit();
				}
				else{
					connection.rollback();
				}
				connection.close();

			} catch (SQLException e) {
				errors.add(e.getMessage());
			}
		}
		return errors;
	}

	protected int getId(PreparedStatement preparedStatement) {
		int id = 0;
		try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return id;

	}
}


