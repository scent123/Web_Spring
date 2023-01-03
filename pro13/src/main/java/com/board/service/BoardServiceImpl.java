package com.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// @Log4j 주해는 Lombok 라이브러리를 이용해 로그를 기록하는 Logger 객체를 생성해서 log 변수에 대입
@Log4j

// 계층 구조상 비즈니스 계층(business tier)을 담당하는 객체임을 명시
// → 스프링 컨테이너에 의해 빈(bean)으로 생성돼 관리된다.
@Service

// Lombok 라이브러리를 이용해, 모든 필드를 매개변수로 받는 생성자를 추가
@AllArgsConstructor

public class BoardServiceImpl implements BoardService {
	// 생성자가 하나이면 @Autowired 주해를 설정하지 않아도 스프링 컨테이너가 필요한 빈(Bean)을 주입한다.
	// @Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		log.info("register = " + board);

		// BoardMapper 인터페이스의 insertSelectKey 메서드를 이용해 게시물을 등록
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long id) {
		log.info("get = " + id);

		// BoardMapper 인터페이스의 read 메서드를 이용해 게시물을 조회
		return mapper.read(id);
	}

	@Override
	public boolean update(BoardVO board) {
		log.info("update = " + board);

		// BoardMapper 인터페이스의 update 메서드를 이용해 게시물을 수정
		// → BoardMapper 인터페이스의 update 메서드의 반환 값은 수정된 게시물의 개수이다.
		//   따라서 정상적으로 수정되면 1이 반환될 것이고, 그럼 BoardServiceImpl 클래스의
		//   update 메서드는 true를 반환한다.
		return mapper.update(board) == 1;
	}

	@Override
	public boolean delete(Long id) {
		log.info("delete = " + id);

		// BoardMapper 인터페이스의 delete 메서드를 이용해 게시물을 삭제
		// → BoardMapper 인터페이스의 delete 메서드의 반환 값은 삭제된 게시물의 개수이다.
		//   따라서 정상적으로 삭제되면 1이 반환될 것이고, 그럼 BoardServiceImpl 클래스의
		//   delete 메서드는 true를 반환한다.
		return mapper.delete(id) == 1;
	}

	/*
	@Override
	public List<BoardVO> getList() {
		log.info("getList");

		// BoardMapper 인터페이스의 getList 메서드를 이용해 게시물 목록을 조회
		return mapper.getList();
	}
	 */

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		log.info("getList with criteria = " + criteria);

		// BoardMapper 인터페이스의 getListWithPaging 메서드를 이용해 게시물 목록을 조회
		return mapper.getListWithPaging(criteria);
	}

	@Override
	public int getTotal(Criteria criteria) {
		log.info("getTotal = " + criteria);

		// BoardMapper 인터페이스의 getTotal 메서드를 이용해 전체 게시물의 개수를 조회
		return mapper.getTotal(criteria);
	}
}
