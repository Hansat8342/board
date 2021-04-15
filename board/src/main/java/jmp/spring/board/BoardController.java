package jmp.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVO;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping("/board/list") 
	public void getList(Model model) { //반횐되는게 없으면 저장된 주소를 반환
		
		model.addAttribute("list", service.getList());
		log.info("getList()===");
	}
	
	// 등록페이지
	@GetMapping("/board/register")
	public void Insert() {
		log.info("=========insert");
	}
	
	// 등록한 게시글이 확인을 받고 등록되는 프로세스
	@PostMapping("board/register")
	public String insertExe(BoardVO vo, RedirectAttributes rttr) { //리다이렉트 는 모델과 비슷한 역할
		log.info("==========="+vo); //vo 가 들어왔나 출력
		int res = service.insertBoard(vo); //vo를 삽입
		log.info("=========="+vo);
		
		rttr.addFlashAttribute("resMsg", vo.getBno()+"번 게시글이 등록 되었습니다.");
		return "redirect:/board/list";
	}
	
	@GetMapping({"/board/get", "/board/edit"})
	public void boardGet(BoardVO vo, Model model) {
		//상세정보 조회 , 모델에 담아서 화면에 전달
		model.addAttribute("vo",service.get(vo.getBno()));
	}
}
