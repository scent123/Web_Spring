package com.spring.ex01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.Setter;

@Component		// 스프링 컨테이너에게 이 클래스가 관리해야 하는 대상임을 표시
@Data			// 생성자와 설정자, 접근자, toString 메서드를 자동으로 생성하는 Lombok 라이브러리의 주해
public class Restaurant {
	// @Setter 주해는 컴파일할 때 setChef 메서드를 자동으로 생성한다.
	// @Setter 주해의 onMethod 속성은 생성되는 setChef 메서드에 @Autowired 주해를 추가한다.
	// @Autowired 주해는 스프링 IoC 컨테이너에게 이 필드에 의존 객체(빈)를 주입해달라는 것을
	// 표시한다.
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
