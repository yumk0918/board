package com.board.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/study";
		String id = "yumk";
		String pw = "password";
		try {
			// JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			// 데이터베이스 커넥션 구하기
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	// 데이터베이스에 데이터를 저장하는 메서드 
	public void insert(User user) throws SQLException {
		addUser(user);
	}

	// 데이터베이스에 데이터를 저장하는 메서드 
	public void addUser(User user) throws SQLException {
		String sql="insert into USERS values(?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			// SQL의 틀을 미리 생성해 놓고 값을 나중에 지정 
			// -> 값 변환을 자동, 간결한 코드 (Statment +''+ 해야 함)
			// 쿼리문 실행을 위한 PreparedStament 객체 생성
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			
			// 쿼리문 실행
			pstmt.executeUpdate();
		}finally { // try절이 끝나고 반드시 실행되어야 함
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
	}

	// 데이터베이스에 userId를 조회하는 메서드 
	public User findByUserId(String userId) throws SQLException {
		String sql="select * from USERS where userId=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		// 쿼리문 실행을 위한 PreparedStament 객체 생성
		conn=getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		
		// 쿼리문 실행하고, ResultSet에서 값 읽어오기
		rs=pstmt.executeQuery();
		
		if(!rs.next()) {
			return null;
		}
		else{
			return new User(
					rs.getString("userId"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"));
		}
		}finally { // try절이 끝나고 반드시 실행되어야 함
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
			if(rs!=null)
				rs.close();
		}
	}
	// 계속적인 테스트를 위한 삭제
	public void removeUser(String userId) throws SQLException {
		String sql="delete from USERS where userId = ?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=getConnection();
			pstmt=getConnection().prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
		}finally { // try절이 끝나고 반드시 실행되어야 함
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
	}
	
	// 개인정보수정한 내용을 update 하기
	public void updateUser(User user) throws SQLException {
		String sql = "update USERS set password=?, name=?, email=? where userId=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
		// SQL의 틀을 미리 생성해 놓고 값을 나중에 지정
		// -> 값 변환을 자동, 간결한 코드 (Statment +''+ 해야 함)
		// 쿼리문 실행을 위한 PreparedStament 객체 생성
			conn=getConnection();
			pstmt= getConnection().prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());

			// 쿼리문 실행
			pstmt.executeUpdate();
		}finally { // try절이 끝나고 반드시 실행되어야 함
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
	}
}