package md.klass.application.validation;

import md.klass.application.models.AbstractBaseModel;

import java.util.ArrayList;
import java.util.List;

public interface Validator<T extends AbstractBaseModel>  {
	 List<String> validate(T model);
}
