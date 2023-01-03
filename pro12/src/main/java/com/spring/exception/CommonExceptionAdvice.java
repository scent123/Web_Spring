package com.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

/*
 * @ControllerAdvice 주해는 이 클래스가 컨트롤러의 예외를 처리하는 클래스임을 나타낸다.
 */
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {

	/*
	 * @ExceptionHandler 주해는 속성으로 주어진 자료형의 예외 객체를 처리한다는 것을 나타낸다.
	 * 이 메서드는 try-catch 구문에서 catch 블록에 해당한다.
	 * 여기에서는 자료형을 Exception 클래스로 지정했기 때문에, 모든 예외를 except 메서드를
	 * 이용해 처리할 수 있다.
	 */
	@ExceptionHandler(Exception.class)
	public String except(Exception e, Model model) {
		// 뷰(View)에서 예외 메시지를 출력할 수 있도록 메서드의 매개변수를 통해 Model 객체를 주입 받고
		// 예외 객체를 추가
		model.addAttribute("exception", e);
		log.error(model);

		// 뷰 이름을 별도로 지정
		// → servlet-context.xml 파일의 설정에 따라 src/main/webapp/WEB-INF/views/errorPage.jsp
		//   파일을 이용해 클라이언트에게 응답하게 된다.
		return "errorPage";
	}

	/*
	 * @ExceptionHandler 주해를 이용해, NoHandlerFoundException 예외를 처리하도록 설정
	 * @ResponseStatus 주해를 이용해, HTTP 상태 코드를 404로 설정
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException e) {
		// src/main/webapp/WEB-INF/views/error404.jsp 파일을 뷰로 지정
		return "error404";
	}
}
