package md.klass.application.repository;


import md.klass.application.database.DataSource;
import md.klass.application.mapping.RowMapper;
import md.klass.application.models.AbstractBaseModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * All method with signature "nameOperation()" are implemented with template method pattern
 * @param <T> Any child class of abstract class BaseModel
 * @param <A> Any type which is used as parameter for method findOne()
 * @param <B> Any type which is used as parameter for method findMultiple()
 */
public abstract class AbstractRepository<T extends AbstractBaseModel, A, B>  {

	protected String SQL;
	protected  String tableName;


	public AbstractRepository() {
	}


	protected abstract void insertOperation(PreparedStatement preparedStatement, T model) throws SQLException;

	public void insert(T model) throws SQLException {

		try (Connection connection = DataSource.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			this.insertOperation(preparedStatement, model);
		}

	}


	protected int getId(PreparedStatement preparedStatement) throws SQLException {
		int id = 0;
		try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
			if (rs.next()) {
				id = rs.getInt(1);
			}
		}
		return id;

	}

	public abstract T findOne(A value) throws SQLException;

	public abstract List<T> findMultiple(B value) throws SQLException;


}


