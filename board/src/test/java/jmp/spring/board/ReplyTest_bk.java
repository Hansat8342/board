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
public class ReplyTest_bk {

	@Autowired
	ReplyMapper mapper;
	
	@Test
	public void mapperGetListTest() {
		log.info(mapper.getList(222));
	}
	
	@Test
	public void mapperGetTest() {
		log.info("==========="+mapper.get(21));
	}
	
	@Test
	public void mapperInsertTest() {
		ReplyVo vo = new ReplyVo();
		vo.setBno(222);
		vo.setReply("오늘도 고생하셨습니다!");
		vo.setReplyer("홍길동");
		
		int res = mapper.insert(vo);
		
	}
	
	@Test
	public void mapperUpdateTest() {
		ReplyVo vo = new ReplyVo();
		
		vo.setRno(3);
		vo.setReply("오늘도 고생하셨습니다!");
		vo.setReplyer("박서현");
		
		int res = mapper.update(vo);
		log.info("============="+res);
	}
	
	@Test
	public void mapperDeleteTest() {
		int res = mapper.delete(3);
		log.info("============="+res);
	}
}
