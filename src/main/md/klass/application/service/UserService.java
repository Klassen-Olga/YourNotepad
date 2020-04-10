package md.klass.application.service;

import md.klass.application.models.AbstractBaseModel;
import md.klass.application.models.Account;
import md.klass.application.models.User;
import md.klass.application.repository.AbstractRepository;
import md.klass.application.repository.UserRepository;
import md.klass.application.validation.UserValidator;
import md.klass.application.validation.Validator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.util.List;


public class UserService extends AbstractService<User> {
	private static final Log logger = LogFactory.getLog(UserService.class);


	public UserService(){
		this.validator=new UserValidator();
		this.repository=new UserRepository();
	}
	@Override
	public List<String> validate(User model){
		return this.validator.validate(model);

	}
	@Override
	public List<String> save(Connection connection, User model){
		return this.repository.insert(connection, model);
	}
}
