package jmp.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.vo.AttachFileVo;
import jmp.spring.mapper.AttachFileMapper;
import jmp.spring.service.AttachFileService;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AttachFileTest {

	@Autowired
	AttachFileMapper mapper;
	
	@Autowired
	AttachFileService service;
	
	@Test
	public void getSeq() {
		log.info("================ : "+service.getSeq());
	}
	
	@Test
	public void getList() {
		log.info(service.getList(1));
	}
	
	@Test
	public void insert() {
		AttachFileVo vo = new AttachFileVo();
		vo.setAttachNo(1);
		vo.setFileName("fileName");
		vo.setFileType("N");
		vo.setUploadPath("uploadPath");
		vo.setUuid(mapper.getSeq()+""); // +"" == 숫자를 문자로 치환해주는 간단한 방법
		
		service.insert(vo);
	}
}
