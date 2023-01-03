package com.spring.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTest {
	// @Setter 주해와 @Autowired 주해에 의해 TimeMapper 객체가 주입된다.
	@Setter(onMethod_ = { @Autowired })
	private TimeMapper timeMapper;

	@Test
	public void testGetTime() {
		// Class 객체의 getName 메서드를 이용해 TimeMapper 인터페이스를 구현한 클래스의 이름을 확인
		// 로그를 확인해보면 내부적으로 적절한 클래스가 만들어진 것을 확인할 수 있다.
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}

	@Test
	public void testGetTime2() {
		// Class 객체의 getName 메서드를 이용해 TimeMapper 인터페이스를 구현한 클래스의 이름을 확인
		// 로그를 확인해보면 내부적으로 적절한 클래스가 만들어진 것을 확인할 수 있다.
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime2());
	}
}
