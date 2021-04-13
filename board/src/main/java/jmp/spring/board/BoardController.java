package jmp.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVO;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping("/board/list")
	public void getList(Model model) {
		
		model.addAttribute("list", service.getList());
		log.info("getList()===");
	}
	
	// 등록페이지
	@GetMapping("/board/register")
	public void Insert() {
		
	}
	
	// 등록한 게시글이 확인을 받고 등록되는 프로세스
	@PostMapping("board/register")
	public String insertExe(BoardVO vo) {
		System.out.println(vo); //vo 가 들어왔나 출력
		service.insertBoard(vo); //vo를 삽입
		
		return "redirect:/board/list";
	}
}
