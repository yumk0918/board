package com.board.support;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class ConnectionManagerTest {

	@Test
	public void connection() {
		Connection conn=ConnectionManager.getConnection();
		assertNotNull(conn);
	}
}
