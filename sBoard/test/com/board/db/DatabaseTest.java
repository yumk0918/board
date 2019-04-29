package com.board.db;

import static org.junit.Assert.*;

import org.junit.Test;

import com.board.user.User;
import com.board.user.UserTest;

public class DatabaseTest {
	/**
	 * 반복적으로 로그인을 하는 것이 어려움 -> TDD
	 * TDD가 성공할 경우 login_action.jsp에 구현하기 
	 */
	@Test // userId가 맞다고 가정할 때 TDD
	public void addAndFind() {
		User user=UserTest.TEST_USER;
		Database.addUser(user);
	
		User dbUser=Database.findUserId(user.getUserId());	
		assertEquals(user, dbUser);
	}
	@Test // userId가 틀렸다고 가정할 때 TDD
	public void addAndFindWhenNotExisted() {
		User dbUser=Database.findUserId("userId2");	
		assertNull(dbUser);
	}

}
