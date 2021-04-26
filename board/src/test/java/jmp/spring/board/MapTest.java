package jmp.spring.board;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jmp.spring.vo.BoardVO;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@Log4j
public class MapTest {
	
	@Test
	public void MapTest() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(111);
		ReplyVo replyVo = new ReplyVo();
		replyVo.setRno(23);
		
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("boardVO", boardVO);
		map.put("replyVo", replyVo);
		
		log.info(map.toString());
		
		log.info("map.size() : "+map.size());
		log.info("boardVO : "+map.get("boardVO"));
		log.info("replyVo : "+map.get("replyVo"));
	}
}
