package jmp.spring.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

import jmp.spring.mapper.BoardMapper;
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
	
	@Test
	public void mapper() {
		log.info("====="+mapper.getTime());
		log.info("====="+mapper.getTime2());
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
