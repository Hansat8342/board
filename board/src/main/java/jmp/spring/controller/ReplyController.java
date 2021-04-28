package jmp.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jdk.internal.org.jline.utils.Log;
import jmp.spring.service.ReplyService;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.PageNavi;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ReplyController {

	@Autowired
	ReplyService service;
	
	@PostMapping("/reply/update")
	/**
	 * 리플 업데이트
	 * 
	 * Json 형식으로 전달받은 데이터를 ReplyVo에 알맞게 넣어줍니다.
	 * @param vo
	 * @return 업데이트 결과 Map<String, String>
	 */
	public Map<String, String> update(@RequestBody ReplyVo vo) {
		log.info("===============/reply/update  시작");

		// 업데이트 로직 호출
		int res = service.update(vo);
		
		// 결과로 리턴해줄  맵 생성
		Map<String, String> map = new HashMap<String, String>();
		
		if(res>0)
			map.put("result", "success");
		else
			map.put("result", "fail");
		
		return map;
		
	}
	
	@GetMapping("/reply/delete/{rno}")
	public Map<String, String> delete(@PathVariable("rno") int rno) {
		int res = service.delete(rno);
		
		Map<String, String> map = new HashMap<String, String>();
		
		if(res>0)
			map.put("result", "success");
		else
			map.put("result", "fail");
		
		return map;
	}
	
	
	@GetMapping("/reply/get/{rno}")
	public ReplyVo get(@PathVariable("rno") int rno) {
		ReplyVo vo = service.get(rno);
		
		return vo;
	}
	
	@GetMapping("/reply/list/{bno}/{pageNo}")
	public Map<String, Object> getList(@PathVariable("bno") int bno
			, @PathVariable("pageNo") int pageNo) {
		
		Criteria cri = new Criteria(pageNo, 10); 

		// 페이징 처리
		PageNavi pageNavi = new PageNavi(cri, service.getTotal(bno));
		
		// 리스트 조회
		List<ReplyVo> list = service.getList(bno, cri);
		
		// 결과를 맵에 담아 리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNavi", pageNavi);
		map.put("list", list);
		
		// 1. 로그를 찍는다
		// 2. 쿼리를 확인한다
		// 3. 쿼리를 디벨로퍼에서 실행해본다
		log.info("============"+ list);
		return map;
	}
	
	@PostMapping("/reply/insert")
	public Map<String, Object> insert(@RequestBody ReplyVo vo) {
		int res = service.insert(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(res>0)
			map.put("result", "success");
		else
			map.put("result", "fail");
		
		return map;
	}
	
	
//	@Autowired
//	BoardService service;
//	
//	@GetMapping("/reply/test")
//	public ResponseEntity<List<BoardVo>> restTest() {
//		Criteria cri = new Criteria();
//		return new ResponseEntity<List<BoardVo>>(service.getList(cri),HttpStatus.OK);
//		//return service.getList(cri);
//	}
//	
//	@GetMapping("/reply/test/{pageNo}")
//	public ResponseEntity<List<BoardVo>> restPathTest(
//			@PathVariable("pageNo") int pageNo) {
//		
//		Criteria cri = new Criteria(pageNo, 20);
//		return new ResponseEntity<List<BoardVo>>(service.getList(cri),HttpStatus.OK);
//		//return service.getList(cri);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
