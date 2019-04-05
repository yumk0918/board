package com.board.support;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	void setParameters(PreparedStatement pstmt) throws SQLException;
}
