package com.spring.ex01;

import org.springframework.stereotype.Component;
import lombok.Data;

@Component	// 스프링 컨테이너에게 이 클래스가 관리해야 하는 대상임을 표시
@Data		// 생성자와 설정자, 접근자, toString 메서드를 자동으로 생성하는 Lombok 라이브러리의 주해
public class Chef {
}
