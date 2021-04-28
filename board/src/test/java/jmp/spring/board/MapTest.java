package jmp.spring.board;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.sun.tools.sjavac.Log;

import jmp.spring.vo.BoardVo;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@Log4j
public class MapTest {

	@Test
	public void  MapTest() {
		BoardVo boardVo = new BoardVo();
		boardVo.setBno(222);
		
		ReplyVo replyVo = new ReplyVo();
		replyVo.setRno(123);
		
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("boardVo", boardVo);
		map.put("replyVo", replyVo);
		
		log.info(map.toString());
		
		log.info("map.size()"+map.size());
		log.info("boardVo : " + map.get("boardVo"));
		log.info("replyVo : " + map.get("replyVo"));
		
		
		
		
		
		
		
	}
	
	
	
	
}
