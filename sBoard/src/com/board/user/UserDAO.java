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
		
		// SQL의 틀을 미리 생성해 놓고 값을 나중에 지정 
		// -> 값 변환을 자동, 간결한 코드 (Statment +''+ 해야 함)
		// 쿼리문 실행을 위한 PreparedStament 객체 생성
		PreparedStatement pstmt=getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		
		// 쿼리문 실행
		pstmt.executeUpdate();
	}

	// 데이터베이스에 userId를 조회하는 메서드 
	public User findByUserId(String userId) throws SQLException {
		String sql="select * from USERS where userId=?";
		
		// 쿼리문 실행을 위한 PreparedStament 객체 생성
		PreparedStatement pstmt=getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);
		
		// 쿼리문 실행하고, ResultSet에서 값 읽어오기
		ResultSet rs=pstmt.executeQuery();
		
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
	}
	public void removeUser(String userId) throws SQLException {
		String sql="delete from USERS where userId = ?";
		PreparedStatement pstmt=getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.executeUpdate();
	}
}