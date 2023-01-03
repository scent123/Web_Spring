package com.spring.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTest {

	static {
		try {
			// 오라클 JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection() {
		Connection connection = null;

		try {
			// 오라클 DBMS에 연결해서 Connection 객체 생성
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"c##scott",
					"tiger");

			log.info("connection = " + connection);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
		finally {
			// Connection 객체 해제
			if (connection != null) try { connection.close(); } catch (Exception e) {}
		}
	}
}
