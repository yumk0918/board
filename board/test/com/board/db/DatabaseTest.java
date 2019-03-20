package com.board.db;
import static org.junit.Assert.*;
import org.junit.Test;

import com.board.user.User;
import com.board.user.UserTest;

public class DatabaseTest {

	@Test
	public void addAndFindWhenExisted() {
		User user=UserTest.TEST_USER;
		Database.addUser(user);
		
		User dbUser=Database.findByUserID(user.getUserId());
		assertEquals(user, dbUser);
	}

		
	public void addAndFindWhenNotExisted() {
		User dbUser=Database.findByUserID("userId2");
		assertNull(dbUser);
	} 
}
