package com.board.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/*
 * 이 테스트 코드는 spring-test 모듈을 이용해 간단하게 스프링 컨테이너(ApplicationContext
 * 객체)를 가동한다. 이때 JUnit 라이브러리는 반드시 4.10 이상의 버전을 사용해야 한다.
 */

/*
 * @RunWith 주해를 이용해, ApplicationContext 객체를 생성하고 관리할 수 있도록 JUnit의 기능을
 * 확장한다. 즉, 스프링 프레임워크의 핵심 기능인 스프링 컨테이너를 생성해서 테스트에서
 * 사용할 수 있도록 해준다.
 */
@RunWith(SpringJUnit4ClassRunner.class)

/*
 * 스프링 MVC의 WebApplicationContext 객체를 사용하기 위해 @WebAppConfiguration 주해를 추가
 */
@WebAppConfiguration

/*
 * @ContextConfiguration 주해를 이용해, 필요한 객체들을 스프링 컨테이너에 빈(Bean)으로 등록
 * 여기에서는 스프링 MVC를 테스트해야 하기 때문에 servlet-context.xml도 불러온다.
 * @ContextConfiguration 주해에는 'classpath:'나 'file:' 경로를 사용할 수 있으며,
 * 이클립스에서 생성해주는 root-context.xml 파일의 경로를 지정하면 된다.
 */
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
})

/*
 * @Log4j 주해는 Lombok 라이브러리를 이용해, 로그를 기록하는 Logger 객체를 생성해서 log 변수에 대입
 * Logger 객체를 선언하지 않아도 Log4j 라이브러리와 설정이 있으면 바로 사용할 수 있다.
 * 이클립스에서 "Spring Legacy Project"를 이용해 프로젝트를 생성하면, 기본적으로 Log4j
 * 라이브러리와 설정이 끝난 상태이므로 Logger 객체를 바로 사용할 수 있다.
 * 로그에 대한 설정은 src/main/resources 폴더와 src/test/resources 폴더에 있다.
 */
@Log4j

public class BoardControllerTest {
	// @Autowired 주해를 통해 스프링 MVC의 WebApplicationContext 객체를 주입 받는다.
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext context;

	/*
	 * MockMvc 객체는 스프링 MVC를 테스트하기 위한 가상 MVC를 나타낸다.
	 * MockMvc 객체를 이용하면, URL과 요청 매개변수 등을 브라우저에서 사용하는 것처럼 만들어서
	 * 컨트롤러를 테스트할 수 있다.
	 */
	private MockMvc mockMvc;

	/*
	 * @Before 주해가 설정된 메서드는 모든 테스트 전에 매번 실행된다.
	 */
	@Before
	public void setup() {
		// 스프링 MVC의 WebApplicationContext 객체를 이용해 가상 MVC를 생성
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testList() throws Exception {
		/*
		 * 1. MockMvcRequestBuilders 클래스의 get 메서드를 이용해 GET 방식의 요청을 생성
		 * 2. MockMvc 객체의 perform 메서드를 이용해, 요청을 수행(ResultActions 객체를 반환)
		 *    → MockMvc 객체의 perform 메서드가 실행되는 중에 예외가 발생할 수 있으므로,
		 *      테스트 메서드에서 throws 구문을 이용해 예외를 전달하도록 한다.
		 * 3. ResultActions 객체의 andReturn 메서드를 이용해, 실행한 요청의 결과(MvcResult 객체)를 생성
		 * 4. MvcResult 객체의 getModelAndView 메서드를 이용해, 요청 결과에서 ModelAndView 객체를 생성
		 * 5. ModelAndView 객체의 getModelMap 메서드를 이용해, ModelMap 객체를 생성
		 */
		/*
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
		 */
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("page", "3")
						.param("rowsPerPage", "10")
						).andReturn().getModelAndView().getModelMap());
	}

	@Test
	public void testWrite() throws Exception {
		// MockMvcRequestBuilders 클래스의 post 메서드를 이용해 POST 방식의 요청을 생성
		// MockHttpServletRequestBuilder 객체의 param 메서드를 이용해 요청 매개변수를 설정
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/board/write")
				.param("title", "[TEST] BoardControllerTest#testWrite")
				.param("content", "[TEST] BoardControllerTest#testWrite")
				.param("writer", "JUnit");

		// ModelAndView 객체의 getViewName 메서드를 이용해 요청 결과에서 뷰 이름을 확인
		String viewName = mockMvc.perform(builder)
				.andReturn().getModelAndView().getViewName();

		log.info("viewName = " + viewName);
	}

	@Test
	public void testRead() throws Exception {
		// param 메서드를 이용해 요청 매개변수를 설정할 때는 문자열로만 처리해야 한다.
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("id", "99"))
				.andReturn().getModelAndView().getModelMap());
	}

	@Test
	public void testUpdate() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/board/update")
				.param("id", "96")
				.param("title", "[TEST] BoardControllerTest#testUpdate")
				.param("content", "[TEST] BoardControllerTest#testUpdate")
				.param("writer", "JUnit");

		String viewName = mockMvc.perform(builder)
				.andReturn().getModelAndView().getViewName();

		log.info("viewName = " + viewName);
	}

	@Test
	public void testDelete() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/board/delete")
				.param("id", "91");

		String viewName = mockMvc.perform(builder)
				.andReturn().getModelAndView().getViewName();

		log.info("viewName = " + viewName);
	}
}
