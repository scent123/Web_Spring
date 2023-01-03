package com.spring.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DataSourceTest {
	// root-context.xml 파일에서 설정한 HikariCP 객체를 주입
	@Setter(onMethod_ = { @Autowired })
	private DataSource dataSource;

	// root-context.xml 파일에서 설정한 SqlSessionFactory 객체를 주입
	@Setter(onMethod_ = { @Autowired })
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void testConnection() {
		// try-with-resource 구문
		// → try 다음에 나오는 괄호 안에서 생성된 자원은 try 블록이 끝나면 자동으로 해제된다.
		try (SqlSession session = sqlSessionFactory.openSession();
				Connection connection = session.getConnection()) {
			log.info("session = " + session);
			log.info("connection = " + connection);
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
