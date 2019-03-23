package com.board.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public Connection getConnection(){
		String url="jdbc:mysql://localhost:3306/study"; //데이터베이스 이름
		String id="root";
		String pw="123456";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,id,pw);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void addUser(User user) throws SQLException {
		String sql="insert into USERS values(?,?,?,?)";
		PreparedStatement pstmt=getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());	
		
		pstmt.executeUpdate();
	}

	public User findByUserId(String userId) throws SQLException {
		String sql="select*from USERS where userid=?";
		PreparedStatement pstmt=getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);
		ResultSet rs=pstmt.executeQuery(); //데이터를 가져와야 하기 때문에
		if(!rs.next()) {
			return null;
		}		
/*		if(rs.next()) {
			User user=new User(
								rs.getString("userId"),
								rs.getString("password"),
								rs.getString("name"), 
								rs.getString("email"));
			return user;
			
		}*/
		return new User(
				rs.getString("userId"),
				rs.getString("password"),
				rs.getString("name"), 
				rs.getString("email"));
	}

	public void removeUser(String userId) throws SQLException {
		String sql="delete from USERS where userId=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);	
		pstmt.executeUpdate();
	}

}
