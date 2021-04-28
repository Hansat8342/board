package jmp.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.ReplyMapper;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyTest {
	
	@Autowired
	ReplyMapper mapper;
	
	@Test
	public void mapperGetListTest() {
		log.info("================="+mapper.getList(4));
	}
	
	@Test
	public void mapperGetTest() {
		log.info("================="+mapper.get(1));
	}
	
	@Test
	public void mapperInsertTest() {
		ReplyVo vo = new ReplyVo();  //vo값을 직접 넣어줘야 함 
		vo.setBno(222);
		vo.setReply("수고하세요");
		vo.setReplyer("홍길동");
		
		int res = mapper.insert(vo);
		
		log.info("================="+res);
	}
	
	@Test
	public void mapperUpdateTest() {
		ReplyVo vo = new ReplyVo();
		vo.setRno(14);  //reply 기본키 rno를 sql에서 확인하기 (몇번 댓글을 바꿀지)
		vo.setReply("고생하셨어요");
		vo.setReplyer("길동이");
		
		int res = mapper.update(vo);
		
		log.info("================="+res);
	}
	
	@Test
	public void mapperDeleteTest() {
		ReplyVo vo = new ReplyVo();
		vo.setRno(1);
		
		int res = mapper.delete(1);
		log.info("================="+res);
	}
	
}
