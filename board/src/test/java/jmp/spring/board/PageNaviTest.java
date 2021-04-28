package jmp.spring.board;

import org.junit.Test;

import jmp.spring.vo.Criteria;
import jmp.spring.vo.PageNavi;
import jmp.spring.vo.PageNavi_bk;
import lombok.extern.log4j.Log4j;

@Log4j
public class PageNaviTest {
	
	@Test
	public void test() {
		
		Criteria cri = new Criteria();
		//cri.setPageNo(15);
		
		PageNavi pageNavi = new PageNavi(cri, 150);
		log.info(pageNavi);
	}
	
}
