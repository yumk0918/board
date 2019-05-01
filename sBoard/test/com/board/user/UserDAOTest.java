package com.board.user;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

	@Test // 커넥션이 생성 했는지 확인하는 테스트
	public void test() {
		Connection con=userDao.getConnection();
		assertNotNull(con);
	}
	
	@Test // insert문 : 회원가입
	public void addUser() throws Exception{
		userDao.addUser(UserTest.TEST_USER);
	}
	
	@Test // select문 : userId조회
	public void findByUserId() throws Exception{
		User user=userDao.findByUserId("userId");
		// equal()메서드가 존재해야 Test가 가능하다.
		assertEquals(UserTest.TEST_USER, user);
	}

}
