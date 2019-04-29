package com.board.db;

import static org.junit.Assert.*;

import org.junit.Test;

import com.board.user.User;
import com.board.user.UserTest;

public class DatabaseTest {
	/**
	 * �ݺ������� �α����� �ϴ� ���� ����� -> TDD
	 * TDD�� ������ ��� login_action.jsp�� �����ϱ� 
	 */
	@Test // userId�� �´ٰ� ������ �� TDD
	public void addAndFind() {
		User user=UserTest.TEST_USER;
		Database.addUser(user);
	
		User dbUser=Database.findUserId(user.getUserId());	
		assertEquals(user, dbUser);
	}
	@Test // userId�� Ʋ�ȴٰ� ������ �� TDD
	public void addAndFindWhenNotExisted() {
		User dbUser=Database.findUserId("userId2");	
		assertNull(dbUser);
	}

}
