package com.board.user;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {
	
	// 계속 커넥션을 만들지 않기 위해서 테스트가 시작할 때 setup을 하는 테스트
	private UserDAO userDao;	
	@Before
	public void setup() {
		userDao = new UserDAO();
	}
	
	@Test // insert문 : 회원가입
	public void addUser() throws Exception{
		crud();
	}

	@Test // insert문 : 회원가입 + delete문 + select문 : userId조회
	public void crud() throws Exception{
		User user=UserTest.TEST_USER;
		// 이전에 테스트에서 생성한 userId를 삭제
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
		
		User dbuser=userDao.findByUserId(user.getUserId());
		// equal()메서드가 존재해야 Test가 가능하다.
		assertEquals(user, dbuser);
		
		// 수정이 됐는지 확인하는 테스트
		User updateUser=new User(user.getUserId(),"uPassword","uName","uemail@naver.com");
		userDao.updateUser(updateUser);
		
		dbuser=userDao.findByUserId(updateUser.getUserId());
		assertEquals(updateUser, dbuser);
	}
	
	@Test // delete문
	public void whenNotUserFind() throws Exception{
		User user=UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		User dbuser=userDao.findByUserId(user.getUserId());
		assertNull(dbuser);
	}
}
