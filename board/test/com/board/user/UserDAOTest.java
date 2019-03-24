package com.board.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;


public class UserDAOTest {
	
	private UserDAO userDao;
	
	//ÀüÃ³¸®
	@Before
	public void setup() {
		userDao = new UserDAO();
	}
	
	@Test
	public void connection() {
		Connection conn=userDao.getConnection();
		assertNotNull(conn);
	}
	
	@Test
	public void crud() throws Exception{
		User user=UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
		
		User dbuser=userDao.findByUserId(user.getUserId());
		assertEquals(UserTest.TEST_USER,dbuser);
		
		User updateUser= new User(user.getUserId(),"uPassword","uName","uEmail@naver.com");
		userDao.updateUser(updateUser);	
		
		dbuser=userDao.findByUserId(updateUser.getUserId());
		assertEquals(updateUser,dbuser);
	}
	
	@Test
	public void findWhenNotUser() throws Exception{
		User user=UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		User dbuser=userDao.findByUserId(user.getUserId());
		assertNull(dbuser);
	}
}
