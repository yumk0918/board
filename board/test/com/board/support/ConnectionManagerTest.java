package com.board.support;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.board.support.jdbc.ConnectionManager;

public class ConnectionManagerTest {

	@Test
	public void connection() {
		Connection conn=ConnectionManager.getConnection();
		assertNotNull(conn);
	}
}
