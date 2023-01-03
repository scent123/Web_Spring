package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageMaker;
import com.board.domain.ResultDTO;
import com.board.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

// 최상위 패키지에 클래스를 추가하고 @Controller 주해를 설정하면, 그 클래스는 스프링 MVC의 컨트롤러로 등록
@Controller

// @RequestMapping 주해를 이용해 요청 URL로 매핑 정보를 설정
@RequestMapping("/board/")

// @Log4j 주해는 Lombok 라이브러리를 이용해, 로그를 기록하는 Logger 객체를 생성해서 log 변수에 대입
@Log4j

// 모든 필드를 매개변수로 받는 생성자를 추가
@AllArgsConstructor
public class BoardController {
	/*
	 * 생성자가 하나이면 @Autowired 주해를 설정하지 않아도 스프링 컨테이너가 필요한 빈(Bean)을 주입한다.
	 */
	// @Setter(onMethod_ = @Autowired)
	private BoardService service;

	/*
	 * 클래스와 메서드에 설정한 @RequestMapping 주해 정보를 결합해 최종 매핑 정보를 생성
	 * 클래스 레벨의 @RequestMapping 주해를 기준으로 삼고, 메서드 레벨의 주해로 세분화한다.
	 */

	/*
	 * "/board/"와 "/board/list"로 들어온 GET 방식의 요청에 매핑
	 */
	// @RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	// @GetMapping(value = { "", "/list" })
	@GetMapping({ "", "/list" })
	/*
	 * 메서드의 매개변수를 Model 클래스로 선언하면, 스프링 MVC에서 생성한 Model 객체를
	 * 메서드에 주입해준다. 메서드에서 뷰(View)로 전달할 데이터를 이 Model 객체에 추가하면,
	 * 스프링 MVC가 Model 객체를 뷰에 전달한다.
	 */
	/*
	public void list(Model model) {
		log.info("list");

		// BoardService 인터페이스의 getList 메서드를 이용해 게시물 목록을 조회해서 Model 객체에 추가
		model.addAttribute("list", service.getList());
	}
	 */
	/*
	public String list(Model model) {
		log.info("list");

		// BoardService 인터페이스의 getList 메서드를 이용해 게시물 목록을 조회해서 Model 객체에 추가
		model.addAttribute("list", service.getList());

		// 뷰(View) 이름을 지정
		return "/board/list";
	}
	 */
	/*
	public String list(Criteria criteria, Model model) {
		log.info("list");

		// BoardService 인터페이스의 getList 메서드를 이용해 게시물 목록을 조회해서 Model 객체에 추가
		model.addAttribute("list", service.getList(criteria));

		// 뷰(View) 이름을 지정
		return "/board/list";
	}
	 */
	public String list(Criteria criteria, Model model) {
		log.info("list");

		// BoardService 인터페이스의 getTotal 메서드를 이용해 전체 게시물의 개수를 조회
		int total = service.getTotal(criteria);

		log.info("total = " + total);

		// BoardService 인터페이스의 getList 메서드를 이용해 게시물 목록을 조회해서 Model 객체에 추가
		model.addAttribute("list", service.getList(criteria));

		// 페이징 정보를 Model 객체에 추가해서 뷰(View)에 전달
		model.addAttribute("pageMaker", new PageMaker(criteria, total));

		// 뷰(View) 이름을 지정
		return "/board/list";
	}

	/*
	 * "/board/write"로 들어온 GET 방식의 요청에 매핑
	 */
	@GetMapping("/write")
	public void write() { }

	/*
	 * "/board/write"로 들어온 POST 방식의 요청에 매핑
	 */
	// @RequestMapping(value = "/write", method = RequestMethod.POST)
	@PostMapping("/write")
	/*
	 * 컨트롤러의 메서드에 자바빈(DTO, VO 등)을 매개변수로 지정하면,
	 * 자바빈의 속성과 이름이 같은 요청 매개변수를 자바빈의 각 속성에 바인딩한다.
	 * RedirectAttributes 객체는 다른 URL로 재지정(redirect)할 때 전달할 값을 저장한다.
	 */
	public String write(BoardVO board, RedirectAttributes rttr) {
		log.info("write = " + board);

		// BoardService 인터페이스의 register 메서드를 이용해 요청 매개변수로 전달된 데이터를 등록
		service.register(board);

		// RedirectAttributes 객체의 addAttribute 메서드를 이용해 URL 재지정할 때 전달할 값을
		// 쿼리 스트링(query string)에 덧붙여서 전달
		rttr.addAttribute("id", board.getId());

		// RedirectAttributes 객체의 addFlashAttribute 메서드를 이용해 URL 재지정할 때
		// 전달할 값을 세션(session)에 1회성 데이터로 저장
		rttr.addFlashAttribute("result", new ResultDTO(true, "write"));

		// 컨트롤러의 메서드에서 문자열을 반환하면 뷰(view) 이름으로 사용된다.
		// 하지만 "redirect:" 접두사를 붙이면 스프링 MVC 내부적으로
		// response.sendRedirect 메서드를 이용해 "/board/read"로 재지정한다.
		return "redirect:/board/read";
	}

	/*
	 * "/board/read"로 들어온 GET 방식의 요청에 매핑
	 */
	@GetMapping("/read")
	/*
	// @RequestParam 주해를 이용해 요청 매개변수 id를 메서드 매개변수 id에 바인딩
	// 뷰에 데이터를 전달하기 위해 Model 객체를 메서드의 매개변수를 통해 주입 받는다.
	public void read(@RequestParam("id") Long id, Model model) {
		log.info("read = " + id);

		// BoardService 인터페이스의 get 메서드를 이용해 게시물을 조회해서 Model 객체에 추가
		model.addAttribute("board", service.get(id));
	}
	 */
	/*
	public BoardVO read(@RequestParam("id") Long id) {
		log.info("read = " + id);

		// BoardService 인터페이스의 get 메서드를 이용해 게시물을 조회해서 반환
		// → 컨트롤러의 메서드에서 객체(Object)를 반환하면 Model 객체에 추가된다.
		//   이때 추가되는 속성의 이름은 반환하는 객체의 클래스 식별자에서 첫 글자를
		//   소문자로 바꾼 이름이 된다. 즉, "boardVO"라는 속성으로 추가된다.
		return service.get(id);
	}
	 */
	public void read(@RequestParam("id") Long id, @ModelAttribute Criteria criteria, Model model) {
		log.info("read = " + id);

		// BoardService 인터페이스의 get 메서드를 이용해 게시물을 조회해서 Model 객체에 추가
		model.addAttribute("boardVO", service.get(id));
	}

	/*
	 * "/board/update"로 들어온 GET 방식의 요청에 매핑
	 */
	@GetMapping("/update")
	/*
	public String update(@RequestParam("id") Long id, Model model) {
		log.info("update = " + id);

		// BoardService 인터페이스의 get 메서드를 이용해 게시물을 조회해서 Model 객체에 추가
		model.addAttribute("board", service.get(id));

		// "/WEB-INF/views/board/write.jsp" 파일을 뷰로 지정
		return "/board/write";
	}
	 */
	public String update(@RequestParam("id") Long id, Criteria criteria, Model model) {
		log.info("update = " + id);

		// BoardService 인터페이스의 get 메서드를 이용해 게시물을 조회해서 Model 객체에 추가
		model.addAttribute("board", service.get(id));
		model.addAttribute("criteria", criteria);

		// "/WEB-INF/views/board/write.jsp" 파일을 뷰로 지정
		return "/board/write";
	}


	/*
	 * "/board/update"로 들어온 POST 방식의 요청에 매핑
	 */
	@PostMapping("/update")
	/*
	public String update(BoardVO board, RedirectAttributes rttr) {
		log.info("update = " + board);

		// BoardService 인터페이스의 update 메서드를 이용해 게시물을 수정
		if (service.update(board))
			// 게시물이 정상적으로 수정되면, 재지정하는 요청에 전달할 값을 세션(session)에
			// 1회성 데이터로 저장
			rttr.addFlashAttribute("result", new ResultDTO(true, "update"));

		// "/board/read"로 재지정하기 위해 요청 매개변수 설정
		rttr.addAttribute("id", board.getId());

		// "/board/read"로 재지정
		return "redirect:/board/read";
	}
	 */
	public String update(BoardVO board, Criteria criteria, RedirectAttributes rttr) {
		log.info("update = " + board);

		// BoardService 인터페이스의 update 메서드를 이용해 게시물을 수정
		if (service.update(board))
			// 게시물이 정상적으로 수정되면, 재지정하는 요청에 전달할 값을 세션(session)에
			// 1회성 데이터로 저장
			rttr.addFlashAttribute("result", new ResultDTO(true, "update"));

		// "/board/read"로 재지정하기 위해 요청 매개변수 설정
		rttr.addAttribute("id", board.getId());
		rttr.addAttribute("page", criteria.getPage());

		// "/board/read"로 재지정
		return "redirect:/board/read";
	}

	/*
	 * "/board/delete"로 들어온 요청에 매핑
	 */
	@RequestMapping("/delete")
	/*
	public String delete(@RequestParam("id") Long id, RedirectAttributes rttr) {
		log.info("delete = " + id);

		// BoardService 인터페이스의 delete 메서드를 이용해 게시물을 삭제
		if (service.delete(id))
			rttr.addFlashAttribute("result", new ResultDTO(true, "delete"));

		// "/board/list"로 재지정
		return "redirect:/board/list";
	}
	 */
	public String delete(@RequestParam("id") Long id, Criteria criteria, RedirectAttributes rttr) {
		log.info("delete = " + id);

		// BoardService 인터페이스의 delete 메서드를 이용해 게시물을 삭제
		if (service.delete(id))
			rttr.addFlashAttribute("result", new ResultDTO(true, "delete"));

		// "/board/list"로 재지정하기 위해 요청 매개변수 설정
		rttr.addAttribute("page", criteria.getPage());

		// "/board/list"로 재지정
		return "redirect:/board/list";
	}
}
