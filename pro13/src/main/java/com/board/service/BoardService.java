package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;

public interface BoardService {
	/**
	 * 게시물을 등록(Create)
	 * @param board 등록할 게시물의 정보
	 */
	public void register(BoardVO board);

	/**
	 * 게시물을 조회(Read)
	 * @param id 조회할 게시물의 id
	 * @return 조회한 게시물의 정보
	 */
	public BoardVO get(Long id);

	/**
	 * 게시물을 수정(Update)
	 * @param board 수정할 게시물의 정보
	 * @return 정상적으로 게시물을 수정했는지 여부
	 */
	public boolean update(BoardVO board);

	/**
	 * 게시물을 삭제(Delete)
	 * @param id 삭제할 게시물의 id
	 * @return 정상적으로 게시물을 삭제했는지 여부
	 */
	public boolean delete(Long id);

	/**
	 * 게시물 목록을 조회(Read)
	 * @return BoardVO 객체들을 담고 있는 List 객체
	 */
	/*
	public List<BoardVO> getList();
	 */

	/**
	 * 게시물 목록을 조회(Read)
	 * @param criteria 페이징 정보
	 * @return BoardVO 객체들을 담고 있는 List 객체
	 */
	public List<BoardVO> getList(Criteria criteria);

	/**
	 * 전체 게시물의 개수를 조회
	 * @param criteria 페이징 정보
	 * @return 전체 게시물의 개수
	 */
	public int getTotal(Criteria criteria);
}
