package com.board.user;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	@Before
	public void setup() throws Exception {
		userDAO = new UserDAO();
		userDAO.removeUser(TEST_USER.getUserId());
	}
	
	// 중복 제거 (public static 선언 : 다른 클래스에서 사용하기 위해)
	public static User TEST_USER=new User("userId", "password","name","email@naver.com");
	private UserDAO userDAO;
	
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
		// Test에 userId를 저장 -> 이전에 있는 userId 삭제해야 함
		userDAO.addUser(TEST_USER);
		assertTrue(User.login(TEST_USER.getUserId(),TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class) // userId 존재하지 않은 경우, 예외처리
	public void loginWhenNotExistedUser() throws Exception{
		User.login("userId2",TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchExpcetion.class) // password 일치하지 않은 경우, 예외처리
	public void loginWhenPasswordMismatch() throws Exception{
		userDAO.addUser(TEST_USER);
		User.login(TEST_USER.getUserId(),"password2");
	}
}