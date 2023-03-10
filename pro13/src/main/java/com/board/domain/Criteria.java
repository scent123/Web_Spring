package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 페이지 처리의 기준이 되는 Criteria 클래스
 */
@Data
@AllArgsConstructor
public class Criteria {
	// 현재 페이지
	private int page;

	// 페이지당 보여줄 게시물의 개수
	private int rowsPerPage;

	// 기본 생성자에서는 기본 값을 설정한다.
	public Criteria() {
		this(1, 10);
	}
}
