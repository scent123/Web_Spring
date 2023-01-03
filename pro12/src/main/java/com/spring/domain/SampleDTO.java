package com.spring.domain;

import lombok.Data;

// Lombok 라이브러리의 @Data 주해를 이용해 생성자, 설정자, 접근자, toString 메서 등을 자동으로 추가
@Data
public class SampleDTO {
	private String name;
	private int age;
}
