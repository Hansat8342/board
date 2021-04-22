package jmp.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.service.ReplyService;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTest {
	
	@Autowired
	ReplyService service;
	
	
	
	@Test
	public void serviceGetListTest() {
		log.info(service.getList(222));
	}
	@Test
	public void serviceGetTest() {
		log.info(service.get(21));
	}
	@Test
	public void serviceUpdateTest() {
		ReplyVo vo = new ReplyVo();
		vo.setRno(21);
		vo.setReply("인서트 테스트3");
		vo.setReplyer("길홍동");
		
		int res = service.update(vo);
	}
	@Test
	public void serviceDeleteTest() {
		int res = service.delete(3);
	}
	@Test
	public void serviceInsertTest() {
		ReplyVo vo = new ReplyVo();
		vo.setBno(111);
		vo.setReply("인서트 테스트");
		vo.setReplyer("홍길동");
		
		int res = service.insert(vo);
	}
}
