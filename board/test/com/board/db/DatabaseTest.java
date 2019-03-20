package com.board.db;
import static org.junit.Assert.*;
import org.junit.Test;

import com.board.user.User;

public class DatabaseTest {

	@Test
	public void addAndFindWhenExisted() {
		User user = new User("userId","password","¿Ã∏ß","aym7330@naver.com");
		Database.addUser(user);
		
		User dbUser=Database.findByUserID(user.getUserId());
		assertEquals(user, dbUser);
	}

		
	public void addAndFindWhenNotExisted() {
		User dbUser=Database.findByUserID("userId2");
		assertNull(dbUser);
	} 
}
