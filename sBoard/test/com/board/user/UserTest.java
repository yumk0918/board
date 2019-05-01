package com.board.user;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {
	
	// 중복 제거 (public static 선언 : 다른 클래스에서 사용하기 위해)
	public static User TEST_USER=new User("userId", "password","name","email@naver.com");
	
	@Test // userId가 이미 있다고 가정 하에 password가 맞는지 확인
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("password"));
	}
	
	@Test // userId가 이미 있다고 가정 하에 password가 틀렸는지 확인
	public void notMatchPassword() {
		assertFalse(TEST_USER.matchPassword("password2"));
	}
	
	@Test // 로그인 성공한 경우
	public void login() throws Exception{
		User user=UserTest.TEST_USER;
		UserDAO userDAO=new UserDAO();
		userDAO.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(),TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class) // userId 존재하지 않은 경우, 예외처리
	public void loginWhenNotExistedUser() throws Exception{
		User.login("userId2",TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchExpcetion.class) // password 일치하지 않은 경우, 예외처리
	public void loginWhenPasswordMismatch() throws Exception{
		User user=UserTest.TEST_USER;
		UserDAO userDAO=new UserDAO();
		userDAO.addUser(user);
		User.login(TEST_USER.getUserId(),"password2");
	}
}