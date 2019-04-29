package com.board.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.board.db.Database;

public class UserTest {
	
	// �ߺ� ���� (public static ���� : �ٸ� Ŭ�������� ����ϱ� ����)
	public static User TEST_USER=new User("userId", "password","name","email@naver.com");
	
	@Test // userId�� �̹� �ִٰ� ���� �Ͽ� password�� �´��� Ȯ��
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("password"));
	}
	
	@Test // userId�� �̹� �ִٰ� ���� �Ͽ� password�� Ʋ�ȴ��� Ȯ��
	public void notMatchPassword() {
		assertFalse(TEST_USER.matchPassword("password2"));
	}
	
	@Test // �α��� ������ ���
	public void login() throws Exception{
		User user=UserTest.TEST_USER;
		Database.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(),TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class) // userId �������� ���� ���, ����ó��
	public void loginWhenNotExistedUser() throws Exception{
		User.login("userId2",TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchExpcetion.class) // password ��ġ���� ���� ���, ����ó��
	public void loginWhenPasswordMismatch() throws Exception{
		User user=UserTest.TEST_USER;
		Database.addUser(user);
		User.login(TEST_USER.getUserId(),"password2");
	}
}
