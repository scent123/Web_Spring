package com.board.domain;

import java.sql.Date;

import lombok.Data;

// Lombok 라이브러리의 @Data 주해를 이용해 생성자와 설정자, 접근자, toString 메서드 등을 추가한다.
@Data
public class BoardVO {
	/*
	 * 테이블의 레코드를 표현하는 VO(Value Object) 클래스는 테이블의 컬럼에 맞춰
	 * 자료형과 식별자를 작성한다.
	 */
	private Long id;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
}
