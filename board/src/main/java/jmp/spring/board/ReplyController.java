package jmp.spring.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jmp.spring.service.ReplyService;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ReplyController {
	
	@Autowired
	ReplyService service; 
	
	@GetMapping("/relpy/list/{bno}")
	public List<ReplyVo> getList(@PathVariable("bno") int bno) {
		List<ReplyVo> list = service.getList(bno);
		
		// 로그를 찍고 쿼리 확인하고 쿠커리를 디벨로퍼에서 따로 실행해보면 오류 확인 가능
		log.info("======="+list);
		return list;
	}
	
	
//	@Autowired
//	BoardService service;
//	
//	@GetMapping("/reply/test")
//	public ResponseEntity<List<BoardVO>> restTest() {
//		Criteria cri = new Criteria();
//		return new ResponseEntity<List<BoardVO>>(service.getList(cri),HttpStatus.INTERNAL_SERVER_ERROR);
//		//return service.getList(cri);
//	}
//	
//	@GetMapping("/reply/test/{pageNo}")
//	public ResponseEntity<List<BoardVO>> restPathTest(@PathVariable("pageNo") int pageNo) {
//		Criteria cri = new Criteria(pageNo, 20);
//		return new ResponseEntity<List<BoardVO>>(service.getList(cri),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
