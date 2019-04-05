package com.board.support;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection(){
		String url="jdbc:mysql://localhost:3306/study"; //데이터베이스 이름
		String id="yumk";
		String pw="password";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,id,pw);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
