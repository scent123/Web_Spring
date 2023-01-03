package com.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.SampleDTO;
import com.spring.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

// 최상위 패키지에 클래스를 추가하고 @Controller 주해를 설정하면, 이 클래스는 스프링 MVC의 컨트롤러로 등록됨
@Controller
// @RequestMapping 주해를 이용해 요청 URL로 매핑 정보를 설정
@RequestMapping("/sample/")
@Log4j
public class SampleController {
	/*
	 * 클래스와 메서드에 붙은 @RequestMapping 주해 정보를 결합해 최종 매핑 정보를 생성
	 * 클래스 레벨의 @RequestMapping 주해를 기준으로 삼고, 메서드 레벨의 @RequestMapping 주해로 세분화
	 */

	/*
	 * "/sample/"로 들어온 요청에 매핑
	 */
	// @RequestMapping("")
	@RequestMapping
	public void index() {
		log.info("SampleController#index()");
	}

	/*
	 * "/sample/basic"으로 들어온 요청 중에 GET 방식만 매핑
	 */
	// @RequestMapping("/basic")
	// @RequestMapping(value = "/basic", method = RequestMethod.GET)
	@GetMapping("/basic")
	public void basicGet() {
		log.info("SampleController#basicGet()");
	}

	/*
	 * "/sample/basic"으로 들어온 요청 중에 POST 방식만 매핑
	 */
	// @RequestMapping(value = "/basic", method = RequestMethod.POST)
	@PostMapping("/basic")
	public void basicPost() {
		log.info("SampleController#basicPost()");
	}

	/*
	 * "/sample/param"으로 들어온 요청에 매핑
	 * "http://localhost:8080/sample/param?name=Jackson&age=21"로 접속해서 로그를 확인
	 */
	@RequestMapping("/param")
	// 컨트롤러의 메서드에 DTO를 매개변수로 지정하면, DTO의 속성과 이름이 같은 요청 매개변수를
	// DTO의 각 속성에 바인딩한다. 이때 @ModelAttribute 주해는 생략할 수 있다.
	public void param(/* @ModelAttribute */ SampleDTO sample) {
		log.info("SampleController#param()");
		log.info("sample = " + sample);
	}

	/*
	 * "/sample/param2"으로 들어온 요청에 매핑
	 * "http://localhost:8080/sample/param2?name=Potter&age=12"로 접속해서 로그를 확인
	 */
	@RequestMapping("/param2")
	// public void param2(@RequestParam("name") String name, @RequestParam("age") int age) {
	public void param2(@RequestParam String name, @RequestParam int age) {
	// public void param2(String name, int age) {
		log.info("SampleController#param2()");
		log.info("name = " + name);
		log.info("age = " + age);
	}

	/*
	 * "/sample/param3"으로 들어온 요청에 매핑
	 * "http://localhost:8080/sample/param3?id=7&id=13&id=19"로 접속해서 로그를 확인
	 */
	@RequestMapping("/param3")
	public void param3(@RequestParam("id") ArrayList<Integer> listId) {
		log.info("SampleController#param3()");
		log.info("listId = " + listId);
	}

	/*
	 * "/sample/param4"으로 들어온 요청에 매핑
	 * "http://localhost:8080/sample/param4?id=7&id=13&id=19"로 접속해서 로그를 확인
	 */
	@RequestMapping("/param4")
	public void param4(@RequestParam("id") int[] arrayId) {
		log.info("SampleController#param4()");

		// Arrays 클래스의 toString 메서드를 이용해 배열을 문자열로 변환
		log.info("arrayId = " + Arrays.toString(arrayId));
	}

	/*
	 * "/sample/param5"으로 들어온 요청에 매핑
	 * "http://localhost:8080/sample/param5?list[0].name=Michael&list[1].name=Harry"로 접속해서 확인
	 * "[" 기호는 "%5B"로 URL 인코딩하고, "]" 기호는 "%5D"로 URL 인코딩해서
	 * "http://localhost:8080/sample/param5?list%5B0%5D.name=Michael&list%5B1%5D.name=Harry"로 접속
	 */
	@RequestMapping("/param5")
	public void param5(SampleDTOList list) {
		log.info("SampleController#param5()");
		log.info("list = " + list);
	}

	/*
	 * "/sample/attr"로 들어온 요청에 매핑
	 * "http://localhost:8080/sample/attr?name=Jackson&age=21&height=180"로 접속해서 확인
	 *
	 * 컨트롤러의 메서드에 DTO를 매개변수로 지정하면, DTO의 속성과 이름이 같은 요청 매개변수가
	 * DTO의 각 속성에 바인딩된다. 바인딩되지 않은 요청 매개변수는 이름이 같은 메서드 매개변수에 바인딩된다.
	 * 이때는 메서드의 매개변수에 @RequestParam 주해가 생략된 형태이다.
	 */
	/*
	@RequestMapping("/attr")
	// public void attr(@ModelAttribute SampleDTO sample, @RequestParam int height) {
	public void attr(SampleDTO sample, int height) {
		log.info("SampleController#attr()");
		log.info("sample = " + sample);
		log.info("height = " + height);
	}
	*/

	/*
	 * "/sample/attr"로 들어온 요청에 매핑
	 * "http://localhost:8080/sample/attr?name=Jackson&age=21&height=180"로 접속해서 확인
	 *
	 * 메서드 매개변수 중 자바빈은 @ModelAttribute 주해를 생략해도 Model 객체에 추가되어 뷰에
	 * 전달되지만, 기본 자료형의 매개변수는 자동으로 Model 객체에 추가되지 않는다.
	 * 이러한 매개변수를 Model 객체에 추가해서 뷰에 전달하기 위해서는, @ModelAttribute 주해를
	 * 설정해야 한다. 이때 추가할 속성의 이름은 생략할 수 없다.
	 */
	@RequestMapping("/attr")
	public void attr(SampleDTO sample, @ModelAttribute("height") int height) {
		log.info("SampleController#attr()");
		log.info("sample = " + sample);
		log.info("height = " + height);
	}
}
