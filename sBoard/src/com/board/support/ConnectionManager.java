package com.board.support;

import java.sql.Connection;
import java.sql.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.board.user.UserDAO;

public class ConnectionManager {
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	// static으로 만들어 객체를 만들 필요 없게 함
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/study?useUnicode=true&characterEncoding=utf-8";
		String id = "yumk";
		String pw = "password";
		try {
			// JDBC 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			// 데이터베이스 커넥션 구하기
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}
	}
}
