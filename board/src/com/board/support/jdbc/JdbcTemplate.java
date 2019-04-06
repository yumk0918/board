package com.board.support.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	public void executeUpdate(String sql, PreparedStatementSetter pss) throws DataAccessException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pss.setParameters(pstmt);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}
		finally {
			try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			} catch(SQLException e) {
				throw new DataAccessException(e);
			}
		}
	}

	public void executeUpdate(String sql, Object... parameters){
		executeUpdate(sql, createPreparedStatementSetter(parameters));
	}

	public <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		List<T> list = list(sql, rm, pss);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameters) throws DataAccessException{
		return executeQuery(sql, rm, createPreparedStatementSetter(parameters));
	}

	public <T> List<T> list(String sql, RowMapper<T> rm, PreparedStatementSetter pss){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pss.setParameters(pstmt);
			rs = pstmt.executeQuery();

			List<T> list = new ArrayList<T>();
			while (rs.next()) {
				list.add(rm.mapRow(rs));
			}
			return list;
		} catch(SQLException e) {
			throw new DataAccessException(e);
		}finally {
			try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}} catch(SQLException e) {
				throw new DataAccessException(e);
			}

		}
	}

	public <T> List<T> list(String sql, RowMapper<T> rm, Object... parameters) {
		return list(sql, rm, createPreparedStatementSetter(parameters));
	}

	private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
		return new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				for (int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i + 1, parameters[i]);
				}
			}
		};
	}
}
