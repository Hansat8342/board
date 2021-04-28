package jmp.spring.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
import jmp.spring.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@org.junit.Test
	public void insertTest() {
		BoardVo board = new BoardVo();
		
		board.setTitle("Test 제목");
		board.setContent("Test 내용");
		board.setWriter("Test 유저");
		boardMapper.insertBoard(board);
	}
	
	@org.junit.Test
	public void deleteTest() {
		int count = boardMapper.delete(14); //제거완료 ? 1..* :0
		log.info(count);
	}
	
	@org.junit.Test
	public void selectTest() {
		BoardVo board = boardMapper.select(7);
		log.info(board);
	}
	
	@Test
	public void getListTest() {
		List<BoardVo> list = boardMapper.getList();
		log.info(list);
	}
	
	@Test
	public void testUpdate() {
		BoardVo board = new BoardVo();
		board.setBno(11);
		board.setTitle("수정된제목");
		board.setContent("수정된내용");
		board.setWriter("user00");
		int count = boardMapper.update(board);
		log.info("UPDATE COUNT : "+count);
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		//10개씩 3페이지
		/*
		 * cri.setPageNum(3); cri.setAmount(10);
		 */
		
		List<BoardVo> list = boardMapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board.getBno()));
	}
	
	@Test
	public void testGetTotal(Criteria cri) {
		log.info("Total : "+boardMapper.getTotal(cri));
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("따뜻");
		cri.setType("TC");
		
		List<BoardVo> list = boardMapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
}
