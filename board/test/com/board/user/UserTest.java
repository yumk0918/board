package com.board.user;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	public static User TEST_USER=new User("userId","password","name","temp@naver.com");
	private UserDAO userDAO; 
	
	@Before
	public void setup() throws SQLException {
		userDAO = new UserDAO();
		userDAO.removeUser(TEST_USER.getUserId());
	}
	@Test
	public void matchPassword() {		
		assertTrue(TEST_USER.matchPassword("password"));
	}
	
	@Test
	public void notmatchPassword() {
		assertFalse(TEST_USER.matchPassword("password2"));
	}
	
	@Test
	public void login() throws Exception {
		User user=UserTest.TEST_USER;
		userDAO.addUser(user);	
		assertTrue(User.login(TEST_USER.getUserId(),TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class )
	public void loginWhenNotExistedUser() throws Exception {
		User.login("userId2",TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class )
	public void loginWhenPasswordMismatch() throws Exception {
		User user=UserTest.TEST_USER;
		userDAO.addUser(user);	
		User.login(TEST_USER.getUserId(),"password2");
	}
}
