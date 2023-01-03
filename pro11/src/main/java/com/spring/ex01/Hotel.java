package com.spring.ex01;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.ToString;

@Component
@Getter						// 접근자를 추가
@ToString					// toString 메서드를 추가
public class Hotel {
	private Chef chef;

	public Hotel(Chef chef) {
		this.chef = chef;
	}
}
