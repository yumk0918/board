package com.board.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.board.user.User;
import com.board.user.UserDAO;

public abstract class JdbcTemplate {

	// insert, update, delete문 관련 template
	// connection 등을 열고 닫고 하는 것이 쉬워짐
	// User객체에 종속, setParameters에 사용 -> 구현할 때 받을 수 있으므로 삭제
	public void executeUpdate(String sql) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			// SQL의 틀을 미리 생성해 놓고 값을 나중에 지정 
			// -> 값 변환을 자동, 간결한 코드 (Statment +''+ 해야 함)
			// 쿼리문 실행을 위한 PreparedStament 객체 생성
			conn=ConnectionManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			setParameters(pstmt);
			
			// 쿼리문 실행
			pstmt.executeUpdate();
		}finally { // try절이 끝나고 반드시 실행되어야 함
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
	}
	
	// UserDAO객체에 종속 -> 추상메서드로 구현을 미룸
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}
