package com.board.user;

import com.board.db.Database;

public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	
	// 생성자 만들기 : 4개의 정보가 다 있어야 User 객체를 만들어야 한다.
	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	// password가 동일한지 확인하는 메서드
	public boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}
	
	// 로그인이 성공했는 확인하는 메서드
	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchExpcetion {
		User user=Database.findUserId(userId);
		if(user==null) {
			throw new UserNotFoundException(); // userId가 존재하지 않을 경우, 예외 발생
		}
		if(!user.matchPassword(password)) {	// 로그인 실패한 경우
			throw new PasswordMismatchExpcetion();
		}
		return true;
	}
	
	
}
