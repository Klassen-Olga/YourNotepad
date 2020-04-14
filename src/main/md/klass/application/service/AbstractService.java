package md.klass.application.service;

import md.klass.application.models.AbstractBaseModel;
import md.klass.application.repository.AbstractRepository;
import md.klass.application.validation.Validator;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractService<T extends AbstractBaseModel, A, B> {

	protected Validator<T> validator;
	protected AbstractRepository<T, A, B> repository;

	public abstract List<String> validate(T model);
	public abstract void save( T model) throws SQLException;


}
