package md.klass.application.service;

import md.klass.application.models.AbstractBaseModel;
import md.klass.application.repository.AbstractRepository;
import md.klass.application.validation.Validator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T extends AbstractBaseModel> {

	protected Validator validator;
	protected AbstractRepository repository;
	public abstract List<String> validate(T model);

	/**
	 *
	 * @param connection because we want to have possibility insert multiple object with the same connection
	 *                   and commit or rollback when we need
	 * @param model
	 * @return
	 */
	public abstract List<String> save(Connection connection, T model);

	public static Connection getConnection(){

		return AbstractRepository.getConnection();
	}
	public static List<String> closeConnection(Connection connection, boolean commit){
		return AbstractRepository.closeConnectionAndCommitOrRollback(connection, commit);
	}
}
