package com.board.support;

import static org.junit.Assert.*;
import java.sql.Connection;
import org.junit.Test;

public class ConnectionManagerTest {

	@Test // 커넥션이 생성했는지 확인하는 테스트
	public void test() {
		Connection con=ConnectionManager.getConnection();
		assertNotNull(con);
	}

}
