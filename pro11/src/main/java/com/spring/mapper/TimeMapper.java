package com.spring.mapper;

import org.apache.ibatis.annotations.Select;

/*
 * XML을 이용해 MyBatis Mapper를 작성할 수 있지만, 이번 예제에서는 Mapper 인터페이스를 직접 정의한다.
 * 이렇게 작성한 인터페이스를 마이바티스 프레임워크에서 인식할 수 있도록,
 * root-context.xml 파일에 설정을 추가한다.
 */
public interface TimeMapper {
	// @Select 주해를 이용해 getTime 메서드에서 실행할 SQL 문을 설정
	// → 아래 SQL 문은 현재 시각을 구해오는 SQL 문이다.
	@Select("SELECT sysdate FROM dual")
	public String getTime();

	// MyBatis 주해를 작성하지 않았지만, src/main/resuources/com/spring/mapper/TimeMapper.xml에
	// 작성해놓은 내용에 따라 getTime2 메서드에 SQL이 추가된다.
	public String getTime2();
}
