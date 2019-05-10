package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

	// insert, update, delete문 관련 template
	// connection 등을 열고 닫고 하는 것이 쉬워짐
	// User객체에 종속, setParameters에 사용 -> 구현할 때 받을 수 있으므로 삭제
	// 추상클래스 -> 인터페이스 
	public void executeUpdate(String sql, PrepareStatementSetter pss) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			// SQL의 틀을 미리 생성해 놓고 값을 나중에 지정 
			// -> 값 변환을 자동, 간결한 코드 (Statment +''+ 해야 함)
			// 쿼리문 실행을 위한 PreparedStament 객체 생성
			conn=ConnectionManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pss.setParameters(pstmt);
			
			// 쿼리문 실행
			pstmt.executeUpdate();
		}catch (SQLException e) {
			throw new DataAccessException(e);
		}
		finally { // try절이 끝나고 반드시 실행되어야 함
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch (SQLException e) {
				throw new DataAccessException(e);
			}
		}
	}
	
	// PrepareStatement를 직접 설정 -> 가변인자 활용 (parameter : 배열) 
	public void executeUpdate(String sql, Object...parameters){
		executeUpdate(sql, createPrepareStatmentSetter(parameters));
	}
	
	// select문 관련 template
	// return User객체에 종속 -> 상위 객체인 Object 바꿈
	public <T>T executeQuery(String sql, RowMapper<T> rm, PrepareStatementSetter pss)  {
			List<T> list=list(sql,rm,pss);
			if(list.isEmpty()) {
				return null;
			}
			return list.get(0);
		}
	
	// 사용자 목록을 조회
	public <T>List<T> list(String sql, RowMapper<T> rm, PrepareStatementSetter pss)  {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		// 쿼리문 실행을 위한 PreparedStament 객체 생성
		conn=ConnectionManager.getConnection();
		pstmt=conn.prepareStatement(sql);
		pss.setParameters(pstmt);
		
		// 쿼리문 실행하고, ResultSet에서 값 읽어오기
		rs=pstmt.executeQuery();
		
		// list에 담기
		List<T> list=new ArrayList<T>();
		while(rs.next()) {
			list.add(rm.mapRow(rs));
		}
		return list;
		}catch (SQLException e) {
			throw new DataAccessException(e);
		}finally { // try절이 끝나고 반드시 실행되어야 함
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
				if(rs!=null)
					rs.close();
			}catch (SQLException e) {
				throw new DataAccessException(e);
			}
		}
	}
	
	// PrepareStatement를 직접 설정 -> 가변인자 활용 (parameter : 배열) 
	// 가변인자를 사용할 경우 : 가변인자를 제일 마지막에 써야 함
	public <T>T executeQuery(String sql, RowMapper<T> rm, Object...parameters) {
		return executeQuery(sql, rm, createPrepareStatmentSetter(parameters));
	}

	public <T>List<T> list(String sql, RowMapper<T> rm, Object...parameters) {
		return list(sql, rm, createPrepareStatmentSetter(parameters));
	}

	
	public PrepareStatementSetter createPrepareStatmentSetter(Object... parameters) {
		return new PrepareStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				for (int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
			}
		};
	}
	
	// UserDAO객체에 종속 -> 추상메서드로 구현을 미룸 
	// -> 2개의 template이 있어 빈 추상메서드를 강제로 만들어야 함
	// -> 인터페이스로 만듦
}
