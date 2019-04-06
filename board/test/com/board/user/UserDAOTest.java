package com.board.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDAOTest {
	 private static final Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
	private UserDAO userDao;
	
	@Before
	public void setup() {
		userDao = new UserDAO();
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
	@Test
	public void findUser() throws Exception{
		List<User> users=userDao.findUsers();
		assertTrue(users.size()>0);
		logger.debug("Users : {}",users);
	}
}
