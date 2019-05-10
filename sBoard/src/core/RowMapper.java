package core;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface // 람다를 사용하기 위해...
public interface RowMapper<T> { // select문에 필요한 ResultSet
	T mapRow(ResultSet rs) throws SQLException;
}
