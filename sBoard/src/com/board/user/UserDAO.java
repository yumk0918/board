package com.board.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.board.support.JdbcTemplate;
import com.board.support.RowMapper;

public class UserDAO {

	// 데이터베이스에 데이터를 저장하는 메서드 
	public void addUser(User user) throws SQLException {
		JdbcTemplate template=new JdbcTemplate();
		String sql="insert into USERS values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(),user.getPassword(),user.getName(), user.getEmail());	
	}

	// 데이터베이스에 userId를 조회하는 메서드 
	public User findByUserId(String userId) throws SQLException {
		RowMapper<User> rm=new RowMapper<User>() {
			
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
					return new User(
							rs.getString("userId"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getString("email"));
			}
		};
		JdbcTemplate template=new JdbcTemplate();
		String sql="select * from USERS where userId=?";
		// (User)로 형변환 -> 제네릭 이용
		return template.executeQuery(sql,rm,userId);
	}

	// 계속적인 테스트를 위한 삭제
	public void removeUser(String userId) throws SQLException {
		String sql="delete from USERS where userId = ?";
		JdbcTemplate template=new JdbcTemplate();
		template.executeUpdate(sql, userId);
	}
	
	// 개인정보수정한 내용을 update하기
	public void updateUser(User user) throws SQLException {
		String sql = "update USERS set password=?, name=?, email=? where userId=?";
		JdbcTemplate template=new JdbcTemplate();
		template.executeUpdate(sql, user.getPassword(),user.getName(),user.getEmail(),user.getUserId());
	}

	public List<User> findUsers() throws SQLException {
		RowMapper<User> rm=new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
					return new User(
							rs.getString("userId"),
							rs.getString("password"),
							rs.getString("name"),
							rs.getString("email"));
			}
		};
		JdbcTemplate template=new JdbcTemplate();
		String sql="select * from USERS";
		// (User)로 형변환 -> 제네릭 이용
		return template.list(sql,rm);
	}
}