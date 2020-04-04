package md.klass.application.repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public abstract class BaseModel {

	protected String SQL;
	protected int id;
	protected Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/YourNotepad?useSSL=false", "debian-sys-maint", "G65C1J4iIxMIxVgF");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	protected abstract void insertOperation(PreparedStatement preparedStatement) throws SQLException;

	public void insert() {
		Connection connection;
		connection = getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

			this.insertOperation(preparedStatement);


		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
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
	public int getId(){
		return this.id;
	}
	public abstract boolean validate(ArrayList<String> errors);

}


