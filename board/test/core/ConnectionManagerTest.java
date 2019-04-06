package core;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import core.jdbc.ConnectionManager;

public class ConnectionManagerTest {

	@Test
	public void connection() {
		Connection conn=ConnectionManager.getConnection();
		assertNotNull(conn);
	}
}
