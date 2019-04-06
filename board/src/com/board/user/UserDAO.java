package com.board.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDAO {
	public void addUser(User user){
		JdbcTemplate template = new JdbcTemplate();
		String sql = "insert into USERS values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
	}

	public User findByUserId(String userId) {
		String sql = "select*from USERS where userid=?";
		RowMapper<User> rm= rs -> 
				new User(
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
		JdbcTemplate template = new JdbcTemplate();
		return template.executeQuery(sql,rm,userId);
	}

	public void removeUser(String userId) {
		String sql = "delete from USERS where userId=?";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(sql, userId);
	}

	public void updateUser(User user) {
		String sql = "update USERS set password=?,name=?,email=? where userId=?";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(sql, user.getPassword(),user.getName(),user.getEmail(),user.getUserId());
	}

	public List<User> findUsers() {
		String sql = "select*from USERS";
		RowMapper<User> rm=rs -> 
		new User(
				rs.getString("userId"), 
				rs.getString("password"), 
				rs.getString("name"),
				rs.getString("email"));
		JdbcTemplate template = new JdbcTemplate();
		return template.list(sql, rm);
	}

}
