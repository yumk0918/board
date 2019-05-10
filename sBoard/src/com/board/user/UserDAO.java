package com.board.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.board.support.JdbcTemplate;
import com.board.support.SelectJdbcTemplate;

public class UserDAO {

	// 데이터베이스에 데이터를 저장하는 메서드 
	public void addUser(User user) throws SQLException {
		JdbcTemplate template=new JdbcTemplate(){ // 내부클래스를 이용해 추상메서드들을 구현
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};
		String sql="insert into USERS values(?,?,?,?)";
		template.executeUpdate(sql);	
	}

	// 데이터베이스에 userId를 조회하는 메서드 
	public User findByUserId(String userId) throws SQLException {
		SelectJdbcTemplate template=new SelectJdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
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
		};
		String sql="select * from USERS where userId=?";
		return (User)template.executeQuery(sql);
	}

	// 계속적인 테스트를 위한 삭제
	public void removeUser(String userId) throws SQLException {
		String sql="delete from USERS where userId = ?";
		JdbcTemplate template=new JdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		template.executeUpdate(sql);
	}
	
	// 개인정보수정한 내용을 update하기
	public void updateUser(User user) throws SQLException {
		String sql = "update USERS set password=?, name=?, email=? where userId=?";
		JdbcTemplate template=new JdbcTemplate() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}
		};
		template.executeUpdate(sql);
	}
}