package com.board.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.board.user.User;
import com.board.user.UserDAO;

public abstract class SelectJdbcTemplate {

	// select문 관련 template
	// return User객체에 종속 -> 상위 객체인 Object 바꿈
	public Object executeQuery(String sql) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		// 쿼리문 실행을 위한 PreparedStament 객체 생성
		conn=ConnectionManager.getConnection();
		pstmt=conn.prepareStatement(sql);
		setParameters(pstmt);
		
		// 쿼리문 실행하고, ResultSet에서 값 읽어오기
		rs=pstmt.executeQuery();
		
		return mapRow(rs);
		}finally { // try절이 끝나고 반드시 실행되어야 함
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
			if(rs!=null)
				rs.close();
		}
	}
	
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;
	public abstract Object mapRow(ResultSet rs) throws SQLException;
	
}
