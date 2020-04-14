package md.klass.application.mapping;

import md.klass.application.models.AbstractBaseModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends AbstractBaseModel> {
	T map(ResultSet resultSet) throws SQLException;
}
