package com.board.support;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> { // select문에 필요한 ResultSet
	T mapRow(ResultSet rs) throws SQLException;
}
