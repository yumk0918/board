package com.board.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.board.db.Database;

public class UserTest {
	public static User TEST_USER=new User("userId","password","�̸�","aym7330@naver.com"); 
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
		Database.addUser(user);	
		assertTrue(User.login(TEST_USER.getUserId(),TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class )
	public void loginWhenNotExistedUser() throws Exception {
		User.login("userId2",TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class )
	public void loginWhenPasswordMismatch() throws Exception {
		User user=UserTest.TEST_USER;
		Database.addUser(user);	
		User.login(TEST_USER.getUserId(),"password2");
	}
}
