package com.board.mapper;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;

public interface BoardMapper {
	/*
	 * @Select 주해를 이용해 getList 메서드에 SQL을 추가
	 * → @Select 주해 등에 SQL을 작성할 때는 마지막에 세미콜론(;)이 들어가지 않도록 주의해야 한다.
	 * → SQL에서 WHERE 절은 테이블을 검색할 때 PK(Primary Key)를 이용하도록 유도한다.
	 */
	// @Select("SELECT * FROM board WHERE id > 0")
	// → Mapper XML을 추가한 다음 @Select 주해를 제거한다.
	public List<BoardVO> getList();

	// 페이징 정보를 담고 있는 Criteria 객체를 매개변수로 전달 받아, 해당 페이지의 게시물 목록을 조회
	public List<BoardVO> getListWithPaging(Criteria criteria);

	// CREATE: 새로운 게시물을 등록(추가한 게시물의 id를 알 필요가 없는 경우)
	public void insert(BoardVO board);

	// CREATE: 새로운 게시물을 등록(추가한 게시물의 id를 알아야 하는 경우)
	public void insertSelectKey(BoardVO board);

	// READ: PK인 id 컬럼을 이용해 특정 게시물을 조회
	public BoardVO read(Long id);

	// UPDATE: PK인 id 컬럼을 이용해 특정 게시물을 갱신
	// → 반환 값 자료형을 int로 지정하면, 갱신한 레코드의 개수를 반환한다.
	public int update(BoardVO board);

	// DELETE: PK인 id 컬럼을 이용해 특정 게시물을 삭제
	// → 반환 값 자료형을 int로 지정하면, 삭제한 레코드의 개수를 반환한다.
	public int delete(Long id);

	// 전체 게시물의 개수를 조회
	public int getTotal(Criteria criteria);
}
