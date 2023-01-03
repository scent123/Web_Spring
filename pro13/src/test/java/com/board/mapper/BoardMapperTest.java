package com.board.mapper;

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
 * 이 테스트 코드는 spring-test 모듈을 이용해 간단하게 스프링 컨테이너를 가동시킨다.
 * 이때 JUnit 라이브러리는 반드시 4.10 이상의 버전을 사용해야 한다.
 */

/*
 * @RunWith 주해를 이용해 ApplicationContext 객체를 만들고 관리할 수 있도록 JUnit의 기능을 확장한다.
 * 스프링 프레임워크의 핵심 기능인 스프링 컨테이너를 생성해서 테스트에서 아용할 수 있도록 해준다.
 */
@RunWith(SpringJUnit4ClassRunner.class)

/*
 * @ContextConfiguration 주해를 이용해 필요한 객체들을 스프링 컨테이너에 빈(Bean)으로 등록
 * @ContextConfiguration 주해에는 "classpath:"나 "file:" 경로를 사용할 수 있으며,
 * 이클립스에서 생성해주는 root-context.xml의 경로를 지정하면 된다.
 */
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

/*
 * @Log4j 주해는 Lombok 라이브러리를 이용해서 로그를 기록하는 Logger 객체를 생성해서 log 변수에 대입
 */
@Log4j

public class BoardMapperTest {
	/*
	 * BoardMapper 인터페이스를 테스트하기 위해 스프링 컨테이너가 생성한 빈을 주입 받는다.
	 * 그렇게 하기 위해 필드를 추가하고 @Setter 주해와 @Autowired 주해를 설정한다.
	 * @Autowired 주해는 스프링 컨테이너에게 이 필드에 의존 객체(빈)을 주입해달라는 것을 나타낸다.
	 */
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	/*
	 * @Test 주해는 JUnit에서 테스트할 대상임을 표시한다.
	 */
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();

		for (BoardVO item : list)
			log.info(item);
	}

	@Test
	public void testGetListWithPaging() {
		// 페이지당 게시물 10개씩, 3페이지의 게시물 목록을 조회
		Criteria criteria = new Criteria(3, 10);

		List<BoardVO> list = mapper.getListWithPaging(criteria);

		for (BoardVO item : list)
			log.info(item);
	}

	@Test
	public void testInsert() {
		// 추가할 게시물, 즉 BoardVO 객체를 생성
		BoardVO board = new BoardVO();

		board.setTitle("[TEST] BoardMapperTest#testInsert");
		board.setContent("[TEST] BoardMapperTest#testInsert");
		board.setWriter("JUnit");

		mapper.insert(board);

		log.info(board);
	}

	@Test
	public void testInsertSelectKey() {
		// 추가할 게시물, 즉 BoardVO 객체를 생성
		BoardVO board = new BoardVO();

		board.setTitle("[TEST] BoardMapperTest#testInsertSelectKey");
		board.setContent("[TEST] BoardMapperTest#testInsertSelectKey");
		board.setWriter("JUnit");

		mapper.insertSelectKey(board);

		log.info(board);
	}

	@Test
	public void testRead() {
		// Mapper 인터페이스의 read 메서드를 이용해 게시물을 조회
		BoardVO board = mapper.read(95L);

		log.info(board);
	}

	@Test
	public void testUpdate() {
		// 갱신할 게시물, 즉 BoardVO 객체를 생성
		BoardVO board = new BoardVO();

		board.setId(98L);
		board.setTitle("[TEST] BoardMapperTest#testUpdate");
		board.setContent("[TEST] BoardMapperTest#testUpdate");
		board.setWriter("JUnit");

		// Mapper 인터페이스의 update 메서드를 이용해 게시물을 갱신
		log.info("Updated records = " + mapper.update(board));
	}

	@Test
	public void testDelete() {
		// Mapper 인터페이스의 delete 메서드를 이용해 게시물을 삭제
		log.info("Deleted records = " + mapper.delete(13L));
	}
}
