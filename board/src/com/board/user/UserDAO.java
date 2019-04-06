package com.board.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.board.support.JdbcTemplate;
import com.board.support.PreparedStatementSetter;
import com.board.support.RowMapper;

public class UserDAO {
	public void addUser(User user) throws SQLException {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "insert into USERS values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "select*from USERS where userid=?";
		RowMapper<User> rm=new RowMapper() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};
		JdbcTemplate template = new JdbcTemplate();
		return template.executeQuery(sql,rm,userId);
	}

	public void removeUser(String userId) throws SQLException {
		String sql = "delete from USERS where userId=?";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(sql, userId);
	}

	public void updateUser(User user) throws SQLException {
		String sql = "update USERS set password=?,name=?,email=? where userId=?";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(sql, user.getPassword(),user.getName(),user.getEmail(),user.getUserId());
	}

	public List<User> findUsers() throws SQLException {
		String sql = "select*from USERS";
		RowMapper<User> rm=new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
			return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};
		JdbcTemplate template = new JdbcTemplate();
		return template.list(sql, rm);
	}

}
