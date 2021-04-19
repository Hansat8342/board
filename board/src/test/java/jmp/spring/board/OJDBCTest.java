package jmp.spring.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

import jmp.spring.mapper.BoardMapper;
import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVO;
import jmp.spring.vo.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
//널 포인트가 뜬다면 밑의 두개 작성했는지 확인.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class OJDBCTest {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "JWEB";
	String pw = "4321";
	
	@Autowired
	HikariDataSource dataSource;
	
	@Autowired
	BoardMapper mapper;
	
	@Autowired
	BoardService service;
	
	@Autowired
	BoardMapper BMapper;
	
	/*
	 * 작성자 :
	 * 작성일 :
	 * 변환값 :
	 */
	
	@Test
	public void getTotal() {
		log.info(service.getTotal());
	}
	
	@Test
	public void getList() {
		Criteria cri = new Criteria();
		
		List<BoardVO> list = service.getList(cri);
		log.info("page===========list"+list);
	}
	
	@Test
	public void deleteMapper() {
		int res = mapper.delete(3);
		
		System.out.println("======="+res);
	}
	
	@Test
	public void updateMapper() {
		BoardVO vo = new BoardVO();
		vo.setBno(3);
		vo.setContent("내용 - update mapperTest");
		vo.setTitle("제목 - update mapperTest");
		vo.setWriter("작성자 - update mapperTest");
		
		int res = mapper.update(vo);
		
		log.info("update test"+res);
	}
	
	@Test
	public void getService() {
		BoardVO vo = service.get(3);
		log.info(vo);
	}
	
	@Test
	public void get() {
		BoardVO vo = mapper.get(3);
		
		log.info(vo);
	}
	
	@Test
	public void BMapper() {
		BoardVO vo = new BoardVO();
		vo.setContent("내용 - mapperTest");
		vo.setTitle("제목 - mapperTest");
		vo.setWriter("작성자 - mapperTest");
		
		int res = BMapper.insertBoard(vo);
		
//		log.info("mapper.insert.test======="+res);
		log.info("service.insertBoard"+service.insertBoard(vo));
	}
	
	@Test
	public void service() {
		//log.info("service======"+service.getList());
	}
	
	@Test
	public void mapper() {
//		log.info(mapper.getList());
//		log.info("====="+mapper.getTime());
//		log.info("====="+mapper.getTime2());
	}
	
	@Test
	public void hikaricpTest() {
		try {
			Connection conn = dataSource.getConnection();
			
			log.info(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void ojdbcTest() {
		try {
			Connection conn = DriverManager.getConnection(url, id, pw);
			log.info(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//testConnection
	
}
