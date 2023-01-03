package com.board.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/*
 * 이 테스트 코드는 spring-test 모듈을 이용해 간단하게 스프링 컨테이너(ApplicationContext
 * 객체)를 가동한다. 이때 JUnit 라이브러리는 반드시 4.10 이상의 버전을 사용해야 한다.
 */

/*
 * @RunWith 주해를 이용해, ApplicationContext 객체를 생성하고 관리할 수 있도록 JUnit의 기능을
 * 확장한다. 즉, 스프링 프레임워크의 핵심 기능인 스프링 컨테이너를 생성해서 테스트에서
 * 사용할 수 있도록 해준다.
 */
@RunWith(SpringJUnit4ClassRunner.class)

/*
 * @ContextConfiguration 주해를 이용해, 필요한 객체들을 스프링 컨테이너에 빈(Bean)으로 등록
 * @ContextConfiguration 주해에는 'classpath:'나 'file:' 경로를 사용할 수 있으며,
 * 이클립스에서 생성해주는 root-context.xml 파일의 경로를 지정하면 된다.
 */
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

/*
 * @Log4j 주해는 Lombok 라이브러리를 이용해, 로그를 기록하는 Logger 객체를 생성해서 log 변수에 대입
 * Logger 객체를 선언하지 않아도 Log4j 라이브러리와 설정이 있으면 바로 사용할 수 있다.
 * 이클립스에서 "Spring Legacy Project"를 이용해 프로젝트를 생성하면, 기본적으로 Log4j
 * 라이브러리와 설정이 끝난 상태이므로 Logger 객체를 바로 사용할 수 있다.
 * 로그에 대한 설정은 src/main/resources 폴더와 src/test/resources 폴더에 있다.
 */
@Log4j

public class BoardServiceTest {
	/*
	 * BoardService 객체를 테스트하기 위해 스프링 컨테이너가 생성한 빈을 주입 받는다.
	 * 그렇게 하기 위해, 필드를 추가하고 @Setter 주해와 @Autowired 주해를 설정한다.
	 * @Autowired 주해는 스프링 프레임워크에게 이 필드에 의존 객체(빈)을 주입해달라는
	 * 것을 나타낸다.
	 */
	@Setter(onMethod_ = { @Autowired })
	private BoardService service;

	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}

	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();

		board.setTitle("[TEST] BoardServiceTest#testRegister");
		board.setContent("[TEST] BoardServiceTest#testRegister");
		board.setWriter("JUnit");

		service.register(board);

		// 등록한 게시물의 id 확인
		log.info("registered id = " + board.getId());
	}

	@Test
	public void testGet() {
		log.info("service.get = " + service.get(33L));
	}

	@Test
	public void testUpdate() {
		// 수정할 게시물을 조회
		BoardVO board = service.get(100L);

		// 게시물이 없으면 테스트 종료
		if (board == null) return;

		// 게시물의 제목을 수정
		board.setTitle("[TEST] BoardServiceTest#testUpdate");

		// BoardService 인터페이스의 update 메서드를 이용해 게시물을 수정
		log.info("service.update = " + service.update(board));
	}

	@Test
	public void testDelete() {
		// 게시물 삭제
		log.info("service.delete = " + service.delete(93L));
	}

	/*
	@Test
	public void testGetList() {
		List<BoardVO> list = service.getList();

		for (BoardVO item : list)
			log.info(item);
	}
	 */

	@Test
	public void testGetList() {
		List<BoardVO> list = service.getList(new Criteria(3, 10));

		for (BoardVO item : list)
			log.info(item);
	}
}
